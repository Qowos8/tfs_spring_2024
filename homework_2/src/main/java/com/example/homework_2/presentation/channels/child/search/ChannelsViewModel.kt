package com.example.homework_2.presentation.channels.child.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.child.mvi.ChannelsEffect
import com.example.homework_2.presentation.channels.child.mvi.ChannelsEvent
import com.example.homework_2.presentation.channels.child.mvi.ChannelsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import vivid.money.elmslie.core.store.Store

class ChannelsViewModel(
    private val channelsStore: Store<
            ChannelsEvent,
            ChannelsEffect,
            ChannelsState>,
) : ViewModel() {

    val currentSearch: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)

    private val _queryState: MutableStateFlow<ChannelsSearchState> = MutableStateFlow(ChannelsSearchState.Init)
    val queryState: StateFlow<ChannelsSearchState> get() = _queryState.asStateFlow()

    val channelsState: StateFlow<ChannelsState> = channelsStore.states

    var isCreate = false
    var currentStreamName = ""
    var currentStreamId = 0

    private val _expandedTopics: MutableMap<Int, List<TopicItem>> = mutableMapOf()
    val expandedTopics: Map<Int, List<TopicItem>> get() = _expandedTopics

    init {
        searchFlow()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchFlow() {
        currentSearch
            .debounce(700L)
            .map { it.trim() }
            .mapLatest { query -> _queryState.emit(ChannelsSearchState.Result(query)) }
            .launchIn(viewModelScope)
    }

    fun loadDBAll() {
        channelsStore.accept(ChannelsEvent.Ui.LoadDBAll)
    }

    fun loadDBSub() {
        channelsStore.accept(ChannelsEvent.Ui.LoadDBSub)
    }

    fun loadDBTopic() {
        channelsStore.accept(ChannelsEvent.Ui.LoadDbTopic(currentStreamId))
    }

    fun updateStreamSub() {
        channelsStore.accept(ChannelsEvent.Ui.UpdateStreamSub)
    }

    fun updateStreamAll() {
        channelsStore.accept(ChannelsEvent.Ui.UpdateStreamAll)
    }

    fun updateTopic() {
        channelsStore.accept(ChannelsEvent.Ui.UpdateTopic(currentStreamId))
    }

    fun searchStream(query: String) {
        channelsStore.accept(ChannelsEvent.Ui.SearchStream(query))
    }

    fun toggleExpandedTopics(topics: List<TopicItem>) {
        _expandedTopics[currentStreamId] = topics
    }

    fun createChannel(name: String, description: String){
        channelsStore.accept(ChannelsEvent.Ui.CreateStream(name, description))
    }
}