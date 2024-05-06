package com.example.homework_2.presentation.profile.me.mvi

sealed interface ProfileCommand {
    object Init : ProfileCommand
    object LoadUser : ProfileCommand
}