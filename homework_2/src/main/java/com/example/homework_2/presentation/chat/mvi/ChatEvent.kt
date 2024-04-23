package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.domain.entity.MessageItem

sealed class ChatEvent {
    sealed class Ui : ChatEvent() {
        class LoadMessages(val topicName: String, val streamName: String): Ui()
        class SendMessage(val streamName: String, val topicName: String, val content: String): Ui()
        class AddReaction(val messageId: Int, val emojiName: String): Ui()
        class DeleteReaction(val messageId: Int, val emojiName: String): Ui()
        object RegisterEvent : Ui()
        object MessagesLoaded: Ui()
    }

    sealed class Domain : ChatEvent() {
        class Success(val value: List<MessageItem>) : Domain()
        object Loading : Domain()
        class Error(val error: String) : Domain()
    }
}