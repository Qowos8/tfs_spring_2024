package com.example.homework_2.presentation.channels.child.search

sealed class SearchState {
    object Init: SearchState()
    class Result(val query: String): SearchState()
    class Error(val error: String): SearchState()
}