package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.domain.entity.MessageItem

sealed class ChatEvent {
    sealed class Ui : ChatEvent() {
        class LoadMessages(val topicName: String, val streamId: Int): Ui()
        class UpdateMessages(val topicName: String, val streamName: String, val streamId: Int,): Ui()
        class SendMessage(val streamName: String, val topicName: String, val content: String): Ui()
        class AddReaction(val messageId: Int, val emojiName: String): Ui()
        class DeleteReaction(val messageId: Int, val emojiName: String): Ui()
        object RegisterEvent : Ui()
    }

    sealed class Domain : ChatEvent() {
        class CacheSuccess(val value: List<MessageItem>) : Domain()
        object CacheEmpty: Domain()
        object CacheLoaded: Domain()
        class UpdateSuccess(val value: List<MessageItem>): Domain()
        object Loading : Domain()
        class Error(val error: String) : Domain()
    }
}