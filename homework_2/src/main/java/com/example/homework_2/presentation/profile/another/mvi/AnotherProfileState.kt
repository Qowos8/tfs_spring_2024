package com.example.homework_2.presentation.profile.another.mvi

import com.example.homework_2.domain.entity.ProfileItem

sealed class AnotherProfileState {
    object Init: AnotherProfileState()
    object Loading: AnotherProfileState()
    class Success(val profileData: ProfileItem): AnotherProfileState()
    class CacheSuccess(val profileData: ProfileItem): AnotherProfileState()
    class Error(val error: String): AnotherProfileState()
}