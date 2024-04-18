package com.example.homework_2.chat

sealed class ChatState {
    object Loading : ChatState()
    object Init: ChatState()
    class Success(val messages: List<MessageItem>): ChatState()
    class Error(val error: String): ChatState()
}