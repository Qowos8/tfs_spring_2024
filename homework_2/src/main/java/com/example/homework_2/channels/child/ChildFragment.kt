package com.example.homework_2.channels.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.channels.OnChildClickListener
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.databinding.ExpandableFragmentBinding
import com.example.homework_2.utils.ObjectHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ChildFragment: Fragment() {
    private lateinit var binding: ExpandableFragmentBinding
    private val viewModel: ChildViewModel by viewModels()
    private var isSubscribe: Boolean = true
    private val handler = ObjectHandler
    private var isCreate = false
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
                if (isSubscribe) addMockSubscribe()
                else addMockAll()
            }
            isCreate = true
        }
    }

    private fun trackState(){
        lifecycleScope.launch {
            viewModel.searchState.collect{ state ->
                when(state){
                    is ChildState.Loading -> {
                        binding.skelet.root.isVisible = true
                    }
                    is ChildState.Error -> {
                        Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_LONG).show()
                    }
                    ChildState.Init -> {}
                    is ChildState.Success -> {
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

    private fun receiveSearchQuery(){
        lifecycleScope.launch {
            handler.getFlow().collect{ viewModel.currentSearch.emit(it) }
        }
    }

    private fun openTopic(topicItem: TopicItem){
        (activity as OnChildClickListener).onTopicClicked(topicItem)
    }

    companion object {
        private const val KEY_WORD = "Subscribe"
        fun newInstance(isSubscribe: Boolean = true): ChildFragment {
            val bundle = Bundle().apply { putBoolean(KEY_WORD, isSubscribe) }
            return ChildFragment().apply { arguments = bundle }
        }
    }
}