package com.example.homework_2.people

sealed class OnlineState {
    object Init : OnlineState()
    object Loading: OnlineState()
    class Success(val list: List<Aggregated>): OnlineState()
    class Error(val error: String): OnlineState()
}