package com.example.homework_2.profile

sealed class UserState {
    object Loading: UserState()
    class Success(val profileData: ProfileItem): UserState()
    class Error(val error: String): UserState()
}