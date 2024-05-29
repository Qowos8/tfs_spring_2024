package com.example.homework_2.presentation.people.mvi

sealed class PeopleCommand {
    object LoadDbPeople: PeopleCommand()
    object UpdatePeople : PeopleCommand()
    class SearchUsers(val query: String): PeopleCommand()
}