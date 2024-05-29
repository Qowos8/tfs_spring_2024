package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.domain.entity.MessageItem

sealed class ChatEvent {
    sealed class Ui : ChatEvent() {
        class Init(val topicName: String, val streamId: Int, val streamName: String): Ui()
        object LoadMessages: Ui()
        object LoadingPage: Ui()
        class UpdateMessages(val nextCount: Int): Ui()
        class SendMessage(val content: String): Ui()
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