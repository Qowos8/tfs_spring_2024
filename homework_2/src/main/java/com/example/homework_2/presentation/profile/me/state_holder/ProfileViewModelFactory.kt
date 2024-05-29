package com.example.homework_2.presentation.profile.me.state_holder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework_2.presentation.profile.me.mvi.ProfileStoreFactory
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(
    private val profileStore: ProfileStoreFactory,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileStore.provide()) as T
    }
}