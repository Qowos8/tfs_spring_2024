package com.example.homework_2.presentation.channels.child.mvi

sealed class ChildEffect {
    class ShowError(val throwable: Throwable) : ChildEffect()
}