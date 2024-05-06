package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.domain.entity.MessageItem

sealed class ChatState {
    object Loading : ChatState()
    object Init: ChatState()
    object CacheLoaded: ChatState()
    class NetworkSuccess(val messages: List<MessageItem>): ChatState()
    class CacheSuccess(val messages: List<MessageItem>): ChatState()
    object CacheEmpty: ChatState()
    class Error(val error: String): ChatState()
}