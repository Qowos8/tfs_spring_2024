package com.example.homework_2.channels.child.subscribe_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.utils.ObjectHandler
import com.example.homework_2.channels.OnChildClickListener
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.channels.child.StreamAdapter
import com.example.homework_2.databinding.ExpandableFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SubscribeFragment : Fragment() {

    private lateinit var binding: ExpandableFragmentBinding
    private val viewModel: SubscribeViewModel by viewModels()
    private val handler = ObjectHandler

    private val adapter: StreamAdapter by lazy {
        StreamAdapter(::openTopic)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExpandableFragmentBinding.inflate(layoutInflater)
        binding.expandableListView.setAdapter(adapter)
        receiveSearchQuery()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addMockSubscribe()
        trackState()
    }

    private fun trackState() {
        lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                when (state) {
                    StreamSubState.Init -> {}

                    is StreamSubState.Loading -> {
                        binding.skelet.root.isVisible = true
                    }

                    is StreamSubState.Error -> {
                        Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_LONG).show()
                    }

                    is StreamSubState.Success -> {
                        if (state.result.isNotEmpty()){
                            binding.apply {
                                skelet.root.isVisible = false
                                expandableListView.isVisible = true
                                emptyListPhrase.isVisible = false
                                adapter.search(state.result)
                            }
                        }
                        else{
                            binding.apply {
                                expandableListView.isVisible = false
                                skelet.root.isVisible = false
                                emptyListPhrase.isVisible = true
                            }
                        }
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

    private fun openTopic(topicItem: TopicItem) {
        (activity as OnChildClickListener).onTopicClicked(topicItem)
    }
}