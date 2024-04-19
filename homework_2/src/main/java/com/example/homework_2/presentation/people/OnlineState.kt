package com.example.homework_2.presentation.people

sealed class OnlineState {
    object Init : OnlineState()
    object Loading: OnlineState()
    class Success(val list: PresenceResponse): OnlineState()
    class Error(val error: String): OnlineState()
}