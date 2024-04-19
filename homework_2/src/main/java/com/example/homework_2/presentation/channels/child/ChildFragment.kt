package com.example.homework_2.presentation.channels.child

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.databinding.ExpandableFragmentBinding
import com.example.homework_2.presentation.channels.child.delegate.ChildAdapter
import com.example.homework_2.presentation.channels.child.delegate.ParentAdapter
import com.example.homework_2.utils.ObjectHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ChildFragment : Fragment() {
    private lateinit var binding: ExpandableFragmentBinding
    private val viewModel: ChildViewModel by viewModels()
    private var isSubscribe: Boolean = true
    private val handler = ObjectHandler
    private var isCreate = false
    private var currentStream = ""
    private var opened = false
    private var currentColor = 0

    private val childAdapter: ChildAdapter by lazy {
        ChildAdapter(::openTopic)
    }
    private val parentAdapter: ParentAdapter by lazy {
        ParentAdapter(::openStream)
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
        trackState()
        receiveSearchQuery()

    }

    override fun onResume() {
        super.onResume()
        if (!isCreate) {
            viewModel.apply {
                if (isSubscribe) viewModel.subscribeStreamsResponse()
                else viewModel.allStreamsResponse()
            }
            isCreate = true
        }
    }

    private fun trackState() {
        lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                when (state) {
                    is ChildState.Loading -> {
                        binding.skelet.root.isVisible = true
                    }

                    is ChildState.Error -> {
                        Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_LONG).show()
                    }

                    ChildState.Init -> {}
                    is ChildState.Success -> {
                        if (state.result.isNotEmpty()) {
                            binding.apply {
                                skelet.root.isVisible = false
                                parentRecycler.isVisible = true
                                emptyListPhrase.isVisible = false
                                parentAdapter.search(state.result)
                                Log.d("childstate", state.result.toString())
                            }
                        } else {
                            binding.apply {
                                parentRecycler.isVisible = false
                                skelet.root.isVisible = false
                                emptyListPhrase.isVisible = true
                            }
                        }
                    }
                }
            }
        }
    }

    private fun trackTopic() {
        lifecycleScope.launch {
            viewModel.topicState.collect { state ->
                when (state) {
                    is com.example.homework_2.presentation.channels.TopicState.Error -> {
                        Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                    }

                    com.example.homework_2.presentation.channels.TopicState.Init -> {

                    }

                    is com.example.homework_2.presentation.channels.TopicState.Success -> {
                        if(binding.childRecycler.isVisible){
                            childAdapter.update(state.topics)
                            childAdapter.setColor(currentColor)
                        }
                    }

                    com.example.homework_2.presentation.channels.TopicState.Loading -> {
                    }
                }
            }
        }
    }

    private fun receiveSearchQuery() {
        lifecycleScope.launch {
            handler.getFlow().collect { viewModel.currentSearch.emit(it) }
        }
    }

    private fun openTopic(topicItem: com.example.homework_2.presentation.channels.TopicItem) {
        (activity as com.example.homework_2.presentation.channels.OnTopicClickListener).onTopicClicked(topicItem, currentStream)

    }

    private fun openStream(streamItem: com.example.homework_2.presentation.channels.AllStreamItem) {
        if (currentStream != streamItem.name) {
            viewModel.getTopics(streamItem.id)
            currentStream = streamItem.name
            currentColor = streamItem.color?.let { Color.parseColor(it) } ?: Color.GRAY
            childAdapter.update(emptyList())
            binding.childRecycler.isVisible = true
            opened = true
            trackTopic()
        } else {
            binding.childRecycler.isVisible = !binding.childRecycler.isVisible
            opened = !opened
            if (binding.childRecycler.isVisible) {
                viewModel.getTopics(streamItem.id)
                currentColor = streamItem.color?.let { Color.parseColor(it) } ?: Color.GRAY
                trackTopic()
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