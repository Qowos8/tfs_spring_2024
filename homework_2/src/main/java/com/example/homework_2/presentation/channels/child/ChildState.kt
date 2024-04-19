package com.example.homework_2.presentation.channels.child

import com.example.homework_2.presentation.channels.AllStreamItem

sealed class ChildState {
    object Init: ChildState()
    object Loading: ChildState()
    class Success(val result: List<AllStreamItem>): ChildState()
    class Error(val errorMessage: String): ChildState()
}