package com.example.homework_2.channels.child.all_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.channels.OnChildClickListener
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.channels.child.StreamAdapter
import com.example.homework_2.databinding.ExpandableFragmentBinding
import com.example.homework_2.utils.ObjectHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AllFragment: Fragment() {
    private lateinit var binding: ExpandableFragmentBinding
    private val viewModel: AllViewModel by viewModels()
    private val handler = ObjectHandler.handler
    private val adapter: StreamAdapter by lazy {
        StreamAdapter(::openTopic)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExpandableFragmentBinding.inflate(layoutInflater)
        binding.expandableListView.setAdapter(adapter)
        receiveSearchQuery()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addMockAll()
        trackState()
    }

    private fun trackState(){
        lifecycleScope.launch {
            viewModel.searchState.collect{ state ->
                when(state){
                    is StreamAllState.Loading -> {
                        binding.skelet.root.isVisible = true
                    }
                    is StreamAllState.Error -> {
                        Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_LONG).show()
                    }
                    StreamAllState.Init -> {}
                    is StreamAllState.Success -> {
                        binding.skelet.root.isVisible = false
                        adapter.update(state.result)
                    }
                }
            }
        }
    }

    private fun receiveSearchQuery(){
        lifecycleScope.launch {
            handler.getFlow().collect{
                viewModel.currentSearch.emit(it)
                Log.d("all", it)
            }
        }
    }

    private fun openTopic(topicItem: TopicItem){
        (activity as OnChildClickListener).onTopicClicked(topicItem)
    }
}