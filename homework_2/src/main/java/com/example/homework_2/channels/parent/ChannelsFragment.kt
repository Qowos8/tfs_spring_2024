package com.example.homework_2.channels.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.utils.ObjectHandler
import com.example.homework_2.channels.child.subscribe_fragment.SubscribeFragment
import com.example.homework_2.channels.child.all_fragment.AllFragment
import com.example.homework_2.databinding.ChannelsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class ChannelsFragment : Fragment() {
    private lateinit var binding: ChannelsFragmentBinding
    private val loginArray = arrayOf("Subscribed", "All streams")

    private val handler = ObjectHandler.handler

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
        initViews()
        sendSearchQuery()
    }

    private fun sendSearchQuery() {
        binding.channelsEditText.addTextChangedListener {
            lifecycleScope.launch {
                it.let { query ->
                    handler.sendFlow(query.toString())
                }
            }
        }
    }

    private fun initViews() {
        val adapter = PagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.channelsViewPager.adapter = adapter

        adapter.update(listOf(SubscribeFragment(), AllFragment()))

        TabLayoutMediator(binding.tabLayout, binding.channelsViewPager) { tab, position ->
            tab.text = loginArray[position]
        }.attach()
    }
}