package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed class PeopleEvent {
    sealed class Ui : PeopleEvent() {
        object Init : Ui()
    }

    sealed class Domain : PeopleEvent() {
        class Success(val value: List<ProfileItem>) : Domain()
        object Loading : Domain()
        class Error(val error: String) : Domain()
    }
}