package com.example.homework_2.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_2.databinding.ChannelsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator


class ChannelsFragment : Fragment() {
    private lateinit var binding: ChannelsFragmentBinding
    private val loginArray = arrayOf("Subscribed", "All streams")
    private val streamsSubscribe: MutableList<StreamItem> = mutableListOf()
    private val streamsAll: MutableList<StreamItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ChannelsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMockSubscribe()
        addMockUnSubscribe()
        initViews()
    }

    private fun initViews() {
        val adapter = PagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.channelsViewPager.adapter = adapter
        adapter.update(listOf(ExpandableFragment(streamsSubscribe), ExpandableFragment(streamsAll)))

        TabLayoutMediator(binding.tabLayout, binding.channelsViewPager) { tab, position ->
            tab.text = loginArray[position]
        }.attach()
    }

    private fun addMockSubscribe(){
        streamsSubscribe.add(
            StreamItem(
                "general",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 1, parentName = "general"),
                    TopicItem("Bruh", 2, 24, parentId = 1, parentName = "general")
                ))
        )

        streamsSubscribe.add(StreamItem(
            "Development",
            true,
            mutableListOf(
                TopicItem("Testing", 1, 1023, parentId = 2, parentName = "Development"),
                TopicItem("Bruh", 2, 24, parentId = 2, parentName = "Development")
            )
        ))
    }

    private fun addMockUnSubscribe(){
        streamsAll.add(
            StreamItem(
                "general",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 1, parentName = "general"),
                    TopicItem("Bruh", 2, 24, parentId = 1, parentName = "general")
                ))
        )

        streamsAll.add(StreamItem(
            "Development",
            true,
            mutableListOf(
                TopicItem("Testing", 1, 1023, parentId = 2, parentName = "Development"),
                TopicItem("Bruh", 2, 24, parentId = 2, parentName = "Development")
            )
        ))
    }
}