package com.example.homework_2.presentation.profile.another.mvi

sealed class AnotherProfileCommand {
    class Init(val userId: Int): AnotherProfileCommand()
    class LoadUser(val userId: Int) : AnotherProfileCommand()
}