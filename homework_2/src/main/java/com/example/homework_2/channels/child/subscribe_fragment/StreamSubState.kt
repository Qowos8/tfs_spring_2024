package com.example.homework_2.channels.child.subscribe_fragment

import com.example.homework_2.channels.StreamItem

sealed class StreamSubState {
    object Init: StreamSubState()
    object Loading: StreamSubState()
    class Success(val result: List<StreamItem>): StreamSubState()
    class Error(val errorMessage: String): StreamSubState()
}