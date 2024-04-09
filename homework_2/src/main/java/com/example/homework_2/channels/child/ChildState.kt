package com.example.homework_2.channels.child

import com.example.homework_2.channels.StreamItem

sealed class ChildState {
    object Init: ChildState()
    object Loading: ChildState()
    class Success(val result: List<StreamItem>): ChildState()
    class Error(val errorMessage: String): ChildState()
}