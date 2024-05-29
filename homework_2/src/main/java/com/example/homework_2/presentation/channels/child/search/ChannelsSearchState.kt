package com.example.homework_2.presentation.channels.child.search

sealed class ChannelsSearchState {
    object Init: ChannelsSearchState()
    class Result(val query: String): ChannelsSearchState()
    class Error(val error: String): ChannelsSearchState()
}