package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed interface ProfileEvent {
    sealed class Ui : ProfileEvent {
        object Init : Ui()
        object LoadUser : Ui()
    }

    sealed interface Domain : ProfileEvent {
        class CacheSuccess(val value: ProfileItem): Domain

        object CacheEmpty : Domain

        class Success(val value: ProfileItem) : Domain

        class Error(val error: String) : Domain
    }
}