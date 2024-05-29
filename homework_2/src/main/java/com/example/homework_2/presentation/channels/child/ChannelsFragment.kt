package com.example.homework_2.presentation.channels.child

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.R
import com.example.homework_2.databinding.CreateChannelAlertDialogBinding
import com.example.homework_2.databinding.ExpandableFragmentBinding
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.OnTopicClickListener
import com.example.homework_2.presentation.channels.child.delegate.StreamDelegate
import com.example.homework_2.presentation.channels.child.delegate.TopicDelegate
import com.example.homework_2.presentation.channels.child.mvi.ChannelsState
import com.example.homework_2.presentation.channels.child.mvi.ChannelsStoreFactory
import com.example.homework_2.presentation.channels.child.search.ChannelsSearchState
import com.example.homework_2.presentation.channels.child.search.ChannelsViewModel
import com.example.homework_2.presentation.channels.child.search.ChannelsViewModelFactory
import com.example.homework_2.presentation.channels.di.ChannelsComponent
import com.example.homework_2.presentation.delegate.MainAdapter
import com.example.homework_2.utils.ObjectHandler
import com.example.homework_2.utils.StreamDelegateMapper.convertToDelegate
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChannelsFragment : Fragment() {
    @Inject
    lateinit var factory: ChannelsStoreFactory

    @Inject
    lateinit var viewModelFactory: ChannelsViewModelFactory
    private val viewModel: ChannelsViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: ExpandableFragmentBinding

    private val isSubscribe: Boolean
        get() = requireArguments().getBoolean(KEY_WORD)

    private val mainAdapter: MainAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainAdapter().apply {
            addDelegate(
                StreamDelegate(
                    onItemClick = { stream ->
                        openStream(stream)
                    })
            )
        }
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
        binding.parentRecycler.adapter = mainAdapter
        binding.addChannel.setOnClickListener {
            showCreateChannelDialog()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveSearchQuery()
        collectResultQuery()
        if (!viewModel.isCreate) {
            if (isSubscribe)
                viewModel.loadDBSub()
            else
                viewModel.loadDBAll()
        }
        render()
    }

    private fun render() {
        viewModel.channelsState.onEach { state ->
            when (state) {
                ChannelsState.Init -> Unit

                ChannelsState.StreamState.Init -> {
                    binding.skelet.root.isVisible = true
                }

                ChannelsState.StreamState.Loading -> {
                    binding.showShimmer()
                }

                ChannelsState.StreamState.EmptyCache -> {
                    binding.skelet.root.isVisible = true
                    if (isSubscribe)
                        viewModel.updateStreamSub()
                    else
                        viewModel.updateStreamAll()
                }

                is ChannelsState.StreamState.Error -> {
                    Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_SHORT).show()
                }

                is ChannelsState.StreamState.CacheSuccess -> {
                    binding.hideShimmer()
                    binding.renderStream(state.result)
                    if (!viewModel.isCreate) {
                        if (isSubscribe)
                            viewModel.updateStreamSub()
                        else
                            viewModel.updateStreamAll()
                        viewModel.isCreate = true
                    }
                }

                ChannelsState.TopicState.Init -> Unit

                ChannelsState.TopicState.EmptyCache -> {
                    viewModel.updateTopic()
                }

                is ChannelsState.TopicState.Error -> {
                    Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                }

                is ChannelsState.TopicState.CacheLoaded -> {
                    viewModel.loadDBTopic()
                }

                is ChannelsState.TopicState.CacheSuccess -> {
                    viewModel.toggleExpandedTopics(state.topics)
                    binding.hideShimmer()
                    binding.renderTopic()
                    binding.skelet.root.isVisible = false
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun ExpandableFragmentBinding.renderStream(streams: List<StreamItem>) {
        skelet.root.isVisible = false
        parentRecycler.isVisible = true
        emptyListPhrase.isVisible = false
        mainAdapter.submitList(convertToDelegate(streams))
    }

    private fun ExpandableFragmentBinding.renderTopic() {
        viewModel.expandedTopics.onEach { topic ->
            if (parentRecycler.isVisible) {
                mainAdapter.toggleTopicsForStream(viewModel.currentStreamId, topic.value)
            }
        }
    }

    private fun receiveSearchQuery() {
        lifecycleScope.launch {
            ObjectHandler.getFlow().collect { query ->
                viewModel.currentSearch.emit(query)
            }
        }
    }

    private fun collectResultQuery() {
        lifecycleScope.launch {
            viewModel.queryState.collect { state ->
                when (state) {
                    is ChannelsSearchState.Error -> Snackbar.make(
                        binding.root,
                        state.error,
                        Snackbar.LENGTH_SHORT
                    ).show()

                    ChannelsSearchState.Init -> Unit
                    is ChannelsSearchState.Result -> {
                        viewModel.searchStream(state.query)
                    }
                }
            }
        }
    }

    private fun openTopic(topicItem: TopicItem) {
        (activity as OnTopicClickListener).onTopicClicked(
            topicItem,
            viewModel.currentStreamName,
            viewModel.currentStreamId,
            topicItem.name
        )
    }

    private fun openStream(streamItem: StreamItem) {
        val topicDelegate = TopicDelegate(
            onItemClick = { topic ->
                openTopic(topic)
            }
        )
        mainAdapter.addDelegate(topicDelegate)
        viewModel.apply {
            currentStreamName = streamItem.name
            currentStreamId = streamItem.id
            loadDBTopic()
        }
        topicDelegate.setColor(
            streamItem.color?.let { Color.parseColor(it) } ?: Color.GRAY
        )
    }

    private fun showCreateChannelDialog() {
        val dialogView = CreateChannelAlertDialogBinding.inflate(layoutInflater)

        AlertDialog.Builder(requireContext(), R.style.CustomAlertDialogTheme)
        .setTitle(R.string.create_channel)
        .setView(dialogView.root)
        .setPositiveButton(getString(R.string.create_word)) { dialog, _ ->
            viewModel.createChannel(
                dialogView.editTextChannelName.text.toString(),
                dialogView.editTextChannelDescription.text.toString()
            )
            dialog.dismiss()
        }
        .setNegativeButton(getString(R.string.cancel_word)) { dialog, _ -> dialog.cancel() }
            .show()
    }

    private fun ExpandableFragmentBinding.hideShimmer() {
        parentRecycler.isVisible = true
        skelet.root.isVisible = false
    }

    private fun ExpandableFragmentBinding.showShimmer() {
        skelet.root.isVisible = false
        parentRecycler.isVisible = false
    }

    companion object {
        private const val KEY_WORD = "Subscribe"
        fun newInstance(isSubscribe: Boolean = true): ChannelsFragment {
            val bundle = Bundle().apply { putBoolean(KEY_WORD, isSubscribe) }
            return ChannelsFragment()
                .apply { arguments = bundle }
        }
    }
}