package com.example.homework_2.profile

sealed class ProfileState {
    object Loading: ProfileState()
    class Success(val profileData: ProfileItem): ProfileState()
    class Error(val error: String): ProfileState()
}