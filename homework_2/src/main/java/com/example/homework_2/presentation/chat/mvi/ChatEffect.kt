package com.example.homework_2.presentation.chat.mvi

sealed class ChatEffect {
    class ShowError(val throwable: Throwable) : ChatEffect()
}