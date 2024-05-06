package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed class PeopleState {
    object Init: PeopleState()

    object Loading: PeopleState()

    class Success(val users: List<ProfileItem>): PeopleState()

    class CacheSuccess(val users: List<ProfileItem>): PeopleState()

    class Error(val error: String): PeopleState()
}
