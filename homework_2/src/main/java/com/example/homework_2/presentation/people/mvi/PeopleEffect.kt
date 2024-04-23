package com.example.homework_2.presentation.people.mvi

sealed class PeopleEffect {
    class ShowError(val throwable: Throwable) : PeopleEffect()
}