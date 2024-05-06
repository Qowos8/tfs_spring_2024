package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed class PeopleEvent {
    sealed class Ui : PeopleEvent() {
        object Init : Ui()
        object LoadUsers: Ui()
    }

    sealed class Domain : PeopleEvent() {
        class CacheSuccess(val value: List<ProfileItem>): Domain()
        object CacheEmpty : Domain()

        class Success(val value: List<ProfileItem>) : Domain()
        class Error(val error: String) : Domain()
    }
}