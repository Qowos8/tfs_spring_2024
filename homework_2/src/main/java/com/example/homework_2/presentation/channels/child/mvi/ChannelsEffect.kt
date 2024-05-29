package com.example.homework_2.presentation.channels.child.mvi

sealed class ChannelsEffect {
    class ShowError(val throwable: Throwable) : ChannelsEffect()
}