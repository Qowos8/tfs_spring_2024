package com.example.homework_2.presentation.profile.me.mvi

sealed interface ProfileEffect {
    object ShowError : ProfileEffect
}