package com.example.homework_2.presentation.people.search

sealed class PeopleSearchState {
    object Init: PeopleSearchState()
    class Result(val query: String): PeopleSearchState()
    class Error(val error: String): PeopleSearchState()
}