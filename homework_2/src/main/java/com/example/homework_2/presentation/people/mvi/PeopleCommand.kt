package com.example.homework_2.presentation.people.mvi

sealed class PeopleCommand {
    object LoadPeople : PeopleCommand()
}