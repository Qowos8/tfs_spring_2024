package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.domain.entity.MessageItem

sealed class ChatState {
    object Loading : ChatState()
    object Init: ChatState()
    class Success(val messages: List<MessageItem>): ChatState()
    class Error(val error: String): ChatState()
}