package com.example.homework_2.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_2.chat.ChatActivity
import com.example.homework_2.databinding.ExpandableFragmentBinding

class ExpandableFragment(private val streams: MutableList<StreamItem>): Fragment() {
    private lateinit var binding: ExpandableFragmentBinding
    private val adapter: StreamAdapter by lazy {
        StreamAdapter(::openTopic)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExpandableFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.expandableListView.setAdapter(adapter)
        adapter.update(streams)
    }

    private fun openTopic(topicItem: TopicItem){
        (activity as OnChildClickListener).onTopicClicked(topicItem)
    }
}