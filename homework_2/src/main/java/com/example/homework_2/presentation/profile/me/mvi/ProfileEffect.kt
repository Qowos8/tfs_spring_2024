package com.example.homework_2.presentation.profile.me.mvi

sealed class ProfileEffect {
    class ShowError(val throwable: Throwable) : ProfileEffect()
}