package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed class ProfileState {
    object Init : ProfileState()

    object Loading : ProfileState()

    data class Success(val profileData: ProfileItem) : ProfileState()

    data class CacheSuccess(val profileData: ProfileItem) : ProfileState()

    data class Error(val error: String) : ProfileState()
}
