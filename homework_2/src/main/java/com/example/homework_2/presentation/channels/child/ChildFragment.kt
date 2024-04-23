package com.example.homework_2.presentation.channels.child

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.R
import com.example.homework_2.databinding.ExpandableFragmentBinding
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.base.ElmBaseFragment
import com.example.homework_2.presentation.channels.OnTopicClickListener
import com.example.homework_2.presentation.channels.child.delegate.ChildAdapter
import com.example.homework_2.presentation.channels.child.delegate.ParentAdapter
import com.example.homework_2.presentation.channels.child.mvi.ChildActor
import com.example.homework_2.presentation.channels.child.mvi.ChildEffect
import com.example.homework_2.presentation.channels.child.mvi.ChildEvent
import com.example.homework_2.presentation.channels.child.mvi.ChildState
import com.example.homework_2.presentation.channels.child.mvi.ChildStoreFactory
import com.example.homework_2.presentation.channels.child.search.ChildViewModel
import com.example.homework_2.presentation.channels.child.search.SearchState
import com.example.homework_2.utils.ObjectHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store

class ChildFragment : ElmBaseFragment<
        ChildEvent,
        ChildEffect,
        ChildState>(R.layout.expandable_fragment) {

    private lateinit var binding: ExpandableFragmentBinding
    private var isSubscribe: Boolean = true
    private val handler = ObjectHandler
    private var isCreate = false
    private var currentStream = ""
    private var opened = false
    private var currentColor = 0

    private val viewModel: ChildViewModel by viewModels()

    private val childAdapter: ChildAdapter by lazy {
        ChildAdapter(::openTopic)
    }
    private val parentAdapter: ParentAdapter by lazy {
        ParentAdapter(::openStream)
    }

    override val store: Store<ChildEvent, ChildEffect, ChildState>
            by elmStoreWithRenderer(elmRenderer = this) {
                ChildStoreFactory(ChildActor()).provide()
            }

    override fun render(state: ChildState) {
        trackState(state)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExpandableFragmentBinding.inflate(layoutInflater)
        binding.parentRecycler.adapter = parentAdapter
        binding.childRecycler.adapter = childAdapter

        isSubscribe = arguments?.getBoolean(KEY_WORD)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveSearchQuery()
        sendQuery()
    }

    override fun onResume() {
        super.onResume()
        if (!isCreate) {
            if (isSubscribe) store.accept(ChildEvent.Ui.LoadStreamSub)
            else store.accept(ChildEvent.Ui.LoadStreamAll)
            isCreate = true
        }
    }

    private fun trackState(state: ChildState) {
        when (state) {
            ChildState.Init -> {}

            ChildState.StreamState.Init -> {
                binding.skelet.root.isVisible = true
            }

            ChildState.StreamState.Loading -> {
                binding.skelet.root.isVisible = true
            }

            is ChildState.StreamState.Error -> {
                Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_LONG).show()
            }

            is ChildState.StreamState.Success -> {
                renderStream(state)
            }

            ChildState.TopicState.Init -> {}
            ChildState.TopicState.Loading -> {}

            is ChildState.TopicState.Error -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
            }

            is ChildState.TopicState.Success -> {
                renderTopic(state)
            }
        }
    }

    private fun renderStream(state: ChildState.StreamState.Success) {
        if (state.result.isNotEmpty()) {
            binding.apply {
                skelet.root.isVisible = false
                parentRecycler.isVisible = true
                emptyListPhrase.isVisible = false
                parentAdapter.search(state.result)
            }
        } else {
            binding.apply {
                parentRecycler.isVisible = false
                skelet.root.isVisible = false
                emptyListPhrase.isVisible = true
            }
        }
    }

    private fun renderTopic(state: ChildState.TopicState.Success) {
        if (binding.childRecycler.isVisible) {
            childAdapter.update(state.topics)
            childAdapter.setColor(currentColor)
        }
    }

    private fun receiveSearchQuery() {
        lifecycleScope.launch {
            handler.getFlow().collect { viewModel.currentSearch.emit(it) }
        }
    }

    private fun sendQuery(){
        lifecycleScope.launch {
            viewModel.queryState.collect{ state ->
                when(state){
                    is SearchState.Error -> Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                    SearchState.Init -> {}
                    is SearchState.Result -> store.accept(ChildEvent.Ui.SearchStream(state.query))
                }
            }
        }
    }

    private fun openTopic(topicItem: TopicItem) {
        (activity as OnTopicClickListener).onTopicClicked(topicItem, currentStream)
    }

    private fun openStream(streamItem: StreamItem) {
        if (currentStream != streamItem.name) {
            store.accept(ChildEvent.Ui.LoadTopic(streamItem.id))
            currentStream = streamItem.name
            currentColor = streamItem.color?.let { Color.parseColor(it) } ?: Color.GRAY
            childAdapter.update(emptyList())
            binding.childRecycler.isVisible = true
            opened = true
        } else {
            binding.childRecycler.isVisible = !binding.childRecycler.isVisible
            opened = !opened
            if (binding.childRecycler.isVisible) {
                store.accept(ChildEvent.Ui.LoadTopic(streamItem.id))
                currentColor = streamItem.color?.let { Color.parseColor(it) } ?: Color.GRAY
            }
        }
    }

    companion object {
        private const val KEY_WORD = "Subscribe"
        fun newInstance(isSubscribe: Boolean = true): ChildFragment {
            val bundle = Bundle().apply { putBoolean(KEY_WORD, isSubscribe) }
            return ChildFragment()
                .apply { arguments = bundle }
        }
    }
}