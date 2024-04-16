package com.example.homework_2.people

import com.example.homework_2.profile.ProfileItem

sealed class PeopleState {
    object Init: PeopleState()
    object Loading: PeopleState()
    class Success(val users: List<ProfileItem>): PeopleState()
    class Error(val error: String): PeopleState()
}