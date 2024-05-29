package com.example.homework_2.presentation.profile.me.state_holder

import androidx.lifecycle.ViewModel
import com.example.homework_2.presentation.profile.me.mvi.ProfileEffect
import com.example.homework_2.presentation.profile.me.mvi.ProfileEvent
import com.example.homework_2.presentation.profile.me.mvi.ProfileState
import kotlinx.coroutines.flow.StateFlow
import vivid.money.elmslie.core.store.Store

class ProfileViewModel (
    private val profileStore: Store<
            ProfileEvent,
            ProfileEffect,
            ProfileState>
): ViewModel() {

    val profileState: StateFlow<ProfileState> = profileStore.states

    init {
        loadProfile()
    }

    fun loadProfile() {
        profileStore.accept(ProfileEvent.Ui.LoadDb)
    }

    fun updateProfile() {
        profileStore.accept(ProfileEvent.Ui.UpdateUser)
    }
}
