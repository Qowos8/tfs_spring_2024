package com.example.homework_2.presentation.people.mvi

sealed class PeopleCommand {
    object Init: PeopleCommand()
    object LoadPeople : PeopleCommand()
}