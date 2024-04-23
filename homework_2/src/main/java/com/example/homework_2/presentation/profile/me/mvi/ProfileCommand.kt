package com.example.homework_2.presentation.profile.me.mvi

sealed class ProfileCommand {
    object LoadUser : ProfileCommand()
}