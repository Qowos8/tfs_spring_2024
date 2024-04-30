package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed interface ProfileEvent {
    sealed class Ui : ProfileEvent {
        object Init : Ui()
    }

    sealed interface Domain : ProfileEvent {
        class Success(val value: ProfileItem) : Domain

        object Loading : Domain

        class Error(val error: String) : Domain
    }
}