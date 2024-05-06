package com.example.homework_2.presentation.profile.another.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed class AnotherProfileEvent {
    sealed class Ui : AnotherProfileEvent() {
        class Init(val userId: Int): Ui()
        class LoadUser(val userId:Int): Ui()
    }

    sealed class Domain : AnotherProfileEvent() {
        class Success(val value: ProfileItem) : Domain()
        class CacheSuccess(val value: ProfileItem): Domain()
        object CacheEmpty: Domain()
        class Error(val error: String) : Domain()
    }
}