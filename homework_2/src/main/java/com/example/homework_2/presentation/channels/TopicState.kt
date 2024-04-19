package com.example.homework_2.presentation.channels

sealed class TopicState {
    object Init : TopicState()
    object Loading: TopicState()
    class Success(val topics: List<TopicItem>): TopicState()
    class Error(val error: String): TopicState()
}