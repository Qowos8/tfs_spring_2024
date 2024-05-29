package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.domain.entity.MessageItem

sealed class ChatState {
    object Loading : ChatState()
    object Init: ChatState()
    object CacheLoaded: ChatState()
    class CacheSuccess(val messages: List<MessageItem>): ChatState()
    object CacheEmpty: ChatState()
    class Error(val error: String): ChatState()
}

data class ChatHolderState(
    val messages: List<MessageItem> = emptyList(),
    var loadingState: LoadingState = LoadingState.Init,
    val streamId: Int = -1,
    val streamName: String = "",
    val topicName: String = "",
    var selectedMessageId: Int = 0,
    val errorMessage: String = ""
)

enum class LoadingState {
    Loading,
    Init,
    CacheLoaded,
    NetworkSuccess,
    CacheSuccess,
    CacheEmpty,
    Error;
}