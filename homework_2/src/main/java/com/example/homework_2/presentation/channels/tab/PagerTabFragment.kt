package com.example.homework_2.presentation.channels.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.utils.ObjectHandler
import com.example.homework_2.presentation.channels.child.ChannelsFragment
import com.example.homework_2.databinding.ChannelsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class PagerTabFragment : Fragment() {

    private lateinit var binding: ChannelsFragmentBinding
    private var searchQuery: String = ""

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
        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(SEARCH_QUERY_KEY) ?: ""
        }
        initViews()
        sendSearchQuery()
        searchQuery?.let {
            binding.channelsEditText.setText(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_QUERY_KEY, searchQuery)
    }

    private fun sendSearchQuery() {
        binding.channelsEditText.addTextChangedListener {
            searchQuery = it.toString()
            lifecycleScope.launch {
                ObjectHandler.sendFlow(searchQuery)
            }
        }
    }

    private fun initViews() {
        val adapter = PagerAdapter(childFragmentManager, lifecycle)
        binding.channelsViewPager.adapter = adapter

        adapter.update(
            listOf(
                ChannelsFragment.newInstance(true),
                ChannelsFragment.newInstance(false),
            )
        )

        TabLayoutMediator(binding.tabLayout, binding.channelsViewPager) { tab, position ->
            tab.text = LOGIN_ARRAY[position]
        }.attach()
    }

    private companion object {
        private const val SEARCH_QUERY_KEY = "Search"
        private const val SUBSCRIBED = "Subscribed"
        private const val ALL = "All streams"
        private val LOGIN_ARRAY = arrayOf(SUBSCRIBED, ALL)
    }
}