package com.example.homework_2.presentation.channels.child

import android.content.Context
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
import com.example.homework_2.presentation.channels.child.mvi.ChildEffect
import com.example.homework_2.presentation.channels.child.mvi.ChildEvent
import com.example.homework_2.presentation.channels.child.mvi.ChildState
import com.example.homework_2.presentation.channels.child.mvi.ChildStoreFactory
import com.example.homework_2.presentation.channels.child.search.ChildViewModel
import com.example.homework_2.presentation.channels.child.search.SearchState
import com.example.homework_2.presentation.channels.di.ChannelsComponent
import com.example.homework_2.utils.ObjectHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChildFragment : ElmBaseFragment<
        ChildEvent,
        ChildEffect,
        ChildState>(R.layout.expandable_fragment) {
    @Inject
    lateinit var factory: ChildStoreFactory

    private lateinit var binding: ExpandableFragmentBinding
    private val handler = ObjectHandler

    private var isSubscribe = true
    private var isErrorShowed = false
    private var isCreate = false
    private var opened = false

    private var currentStream = ""
    private var currentColor = 0
    private var currentStreamId = 0
    private var currentTopicId = 0

    private val viewModel: ChildViewModel by viewModels()

//    private val streamAdapter: StreamAdapter by lazy {
//        StreamAdapter(::openTopic, ::openStream)
//    }

    private val childAdapter: ChildAdapter by lazy {
        ChildAdapter(::openTopic)
    }
    private val parentAdapter: ParentAdapter by lazy {
        ParentAdapter(::openStream)
    }

    override val store: Store<ChildEvent, ChildEffect, ChildState>
            by elmStoreWithRenderer(elmRenderer = this) {
                factory.provide()
            }

    override fun render(state: ChildState) {
        trackState(state)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ChannelsComponent().inject(this)
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
            if (isSubscribe) store.accept(ChildEvent.Ui.InitSub)
            else store.accept(ChildEvent.Ui.InitAll)
        }
    }

    private fun trackState(state: ChildState) {
        when (state) {
            ChildState.Init -> {

            }

            ChildState.StreamState.Init -> {
                binding.skelet.root.isVisible = true
            }

            ChildState.StreamState.EmptyCache -> {
                binding.skelet.root.isVisible = true
                if(isSubscribe) store.accept(ChildEvent.Ui.LoadStreamSub)
                else store.accept(ChildEvent.Ui.LoadStreamAll)
            }

            is ChildState.StreamState.Error -> {
                if (!isErrorShowed){
                    Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_SHORT).show()
                    isErrorShowed = true
                }
                Log.d("shos", state.errorMessage)
            }

            is ChildState.StreamState.Success -> {
                //parentAdapter.search(state.result)
                //renderStream(state.result)
            }

            ChildState.TopicState.Init -> {}
            ChildState.TopicState.EmptyCache -> {}

            is ChildState.TopicState.Error -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
            }

            is ChildState.TopicState.Success -> {
                binding.renderTopic(state.topics)
            }

            is ChildState.StreamState.CacheSuccess -> {
                renderStream(state.result)
                if(!isCreate){
                    if (isSubscribe) store.accept(ChildEvent.Ui.LoadStreamSub)
                    else store.accept(ChildEvent.Ui.LoadStreamAll)
                    isCreate = true
                }

            }

            is ChildState.TopicState.CacheSuccess -> {
                binding.renderTopic(state.topics)
                binding.skelet.root.isVisible = false
            }

            ChildState.StreamState.Loading -> {}
        }
    }

    private fun renderStream(streams: List<StreamItem>) {
        //if (state.result.isNotEmpty()) {
        binding.apply {
            skelet.root.isVisible = false
            parentRecycler.isVisible = true
            emptyListPhrase.isVisible = false
            parentAdapter.search(streams)
        }
    }

    private fun ExpandableFragmentBinding.renderTopic(topics: List<TopicItem>) {
        if (childRecycler.isVisible) {
            childAdapter.submitList(topics)
            Log.d("shish", topics.toString())
            childAdapter.setColor(currentColor)
        }
    }

    private fun receiveSearchQuery() {
        lifecycleScope.launch {
            handler.getFlow().collect { viewModel.currentSearch.emit(it) }
        }
    }

    private fun sendQuery() {
        lifecycleScope.launch {
            viewModel.queryState.collect { state ->
                when (state) {
                    is SearchState.Error -> Snackbar.make(
                        binding.root,
                        state.error,
                        Snackbar.LENGTH_LONG
                    ).show()

                    SearchState.Init -> {}
                    is SearchState.Result -> store.accept(ChildEvent.Ui.SearchStream(state.query))
                }
            }
        }
    }

    private fun openTopic(topicItem: TopicItem) {
        (activity as OnTopicClickListener).onTopicClicked(topicItem, currentStream, currentStreamId, topicItem.name)
    }

    private fun openStream(streamItem: StreamItem) {
        if (currentStream != streamItem.name) {
            store.accept(ChildEvent.Ui.InitTopic(streamItem.id))
            store.accept(ChildEvent.Ui.LoadTopic(streamItem.id))
            currentStream = streamItem.name
            currentStreamId = streamItem.id
            currentColor = streamItem.color?.let { Color.parseColor(it) } ?: Color.GRAY
            childAdapter.submitList(emptyList())
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

    private fun ExpandableFragmentBinding.hideShimmer() {
        parentRecycler.isVisible = false
        skelet.root.isVisible = false
        emptyListPhrase.isVisible = true
    }

    private fun ExpandableFragmentBinding.showShimmer() {
        skelet.root.isVisible = false
        parentRecycler.isVisible = true
        emptyListPhrase.isVisible = false
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