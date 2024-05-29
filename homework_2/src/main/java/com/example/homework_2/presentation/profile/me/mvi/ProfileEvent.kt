package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed interface ProfileEvent {
    sealed class Ui : ProfileEvent {
        object LoadDb : Ui()
        object UpdateUser : Ui()
    }

    sealed interface Domain : ProfileEvent {
        class CacheSuccess(val value: ProfileItem): Domain

        object CacheEmpty : Domain

        class Error(val error: String) : Domain

        object CacheLoaded: Domain
    }
}