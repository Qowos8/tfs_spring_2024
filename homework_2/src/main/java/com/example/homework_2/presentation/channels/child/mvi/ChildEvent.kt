package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem

sealed class ChildEvent {
    sealed class Ui : ChildEvent() {
        object InitAll: Ui()
        object InitSub: Ui()
        class InitTopic(val streamId: Int): Ui()
        object LoadStreamAll : Ui()
        object LoadStreamSub : Ui()
        class LoadTopic(val streamId: Int) : Ui()
        class SearchStream(val query: String) : Ui()
    }

    sealed class Domain : ChildEvent() {

        sealed class Sub : Domain() {
            object Success : Sub()
            class CacheSuccess(val value: List<StreamItem>): Sub()
            object CacheEmpty : Sub()
            object Loading : Sub()
            class Error(val error: String) : Sub()
        }

        sealed class All : Domain() {
            class Success(val value: List<StreamItem>) : All()
            class CacheSuccess(val value: List<StreamItem>): All()
            object CacheEmpty : All()
            object Loading : All()
            class Error(val error: String) : All()
        }

        sealed class Topic : Domain() {
            class Success(val value: List<TopicItem>) : Topic()
            class CacheSuccess(val value: List<TopicItem>): Topic()
            object CacheEmpty : Topic()
            class Error(val error: String) : Topic()
        }
    }
}

