package com.example.homework_2.presentation.profile.another.mvi

sealed class AnotherProfileEffect {
    class ShowError(val throwable: Throwable) : AnotherProfileEffect()
}