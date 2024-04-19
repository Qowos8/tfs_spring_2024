package com.example.homework_2.presentation.profile

import com.example.homework_2.data.network.model.ProfileItem

sealed class ProfileState {
    object Loading: ProfileState()
    class Success(val profileData: ProfileItem): ProfileState()
    class Error(val error: String): ProfileState()
}