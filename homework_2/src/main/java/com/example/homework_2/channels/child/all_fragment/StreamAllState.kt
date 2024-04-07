package com.example.homework_2.channels.child.all_fragment

import com.example.homework_2.channels.StreamItem

sealed class StreamAllState {
    object Init: StreamAllState()
    object Loading: StreamAllState()
    class Success(val result: List<StreamItem>): StreamAllState()
    class Error(val errorMessage: String): StreamAllState()
}