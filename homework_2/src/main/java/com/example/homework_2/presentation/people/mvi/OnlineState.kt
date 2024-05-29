package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.data.network.model.event.PresenceResponse

sealed class OnlineState {
    object Init : OnlineState()
    object Loading: OnlineState()
    class Success(val list: PresenceResponse): OnlineState()
    class Error(val error: String): OnlineState()
}