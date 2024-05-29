package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem

sealed class ChannelsState {
    object Init : StreamState()

    sealed class StreamState : ChannelsState() {
        object Init : StreamState()
        object Loading : StreamState()
        object EmptyCache : StreamState()
        class CacheSuccess(val result: List<StreamItem>) : StreamState()
        class Error(val errorMessage: String) : StreamState()
    }

    sealed class TopicState : ChannelsState() {
        object Init : TopicState()
        object EmptyCache : TopicState()
        object CacheLoaded: TopicState()
        class CacheSuccess(val topics: List<TopicItem>) : TopicState()
        class Error(val error: String) : TopicState()
    }
}