package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem

sealed class ChildEvent {
    sealed class Ui : ChildEvent() {
        object LoadStreamAll : Ui()
        object LoadStreamSub : Ui()
        class LoadTopic(val streamId: Int) : Ui()
        class SearchStream(val query: String) : Ui()
    }

    sealed class Domain : ChildEvent() {

        sealed class Sub : Domain() {
            data class Success(val value: List<StreamItem>) : Sub()
            object Loading : Sub()
            data class Error(val error: String) : Sub()
        }

        sealed class All : Domain() {
            data class Success(val value: List<StreamItem>) : All()
            object Loading : All()
            data class Error(val error: String) : All()
        }

        sealed class Topic : Domain() {
            data class Success(val value: List<TopicItem>) : Topic()
            object Loading : Topic()
            data class Error(val error: String) : Topic()
        }
    }
}

