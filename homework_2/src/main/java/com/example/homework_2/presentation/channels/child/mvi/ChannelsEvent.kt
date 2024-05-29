package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem

sealed class ChannelsEvent {
    sealed class Ui : ChannelsEvent() {
        object LoadDBAll: Ui()
        object LoadDBSub: Ui()
        class LoadDbTopic(val streamId: Int): Ui()
        object UpdateStreamAll : Ui()
        object UpdateStreamSub : Ui()
        class UpdateTopic(val streamId: Int) : Ui()
        class SearchStream(val query: String) : Ui()
        class CreateStream(val name: String, val description: String = ""): Ui()
    }

    sealed class Domain : ChannelsEvent() {

        class ErrorCreate(val error: String): Domain()

        sealed class Sub : Domain() {
            class CacheSuccess(val value: List<StreamItem>): Sub()
            object CacheEmpty : Sub()
            object Loading : Sub()
            class Error(val error: String) : Sub()
        }

        sealed class All : Domain() {
            class CacheSuccess(val value: List<StreamItem>): All()
            object CacheEmpty : All()
            object Loading : All()
            class Error(val error: String) : All()
        }

        sealed class Topic : Domain() {
            object CacheLoaded: Topic()
            class CacheSuccess(val value: List<TopicItem>): Topic()
            object CacheEmpty : Topic()
            class Error(val error: String) : Topic()
            object Loading: Topic()
        }
    }
}

