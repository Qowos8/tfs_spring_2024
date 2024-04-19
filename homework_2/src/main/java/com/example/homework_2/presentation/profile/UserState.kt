package com.example.homework_2.presentation.profile

import com.example.homework_2.data.network.model.ProfileItem

sealed class UserState {
    object Loading: UserState()
    class Success(val profileData: ProfileItem): UserState()
    class Error(val error: String): UserState()
}