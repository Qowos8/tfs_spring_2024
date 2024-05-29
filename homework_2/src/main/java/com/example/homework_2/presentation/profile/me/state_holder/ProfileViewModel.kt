package com.example.homework_2.presentation.profile.me.state_holder

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.homework_2.presentation.profile.me.mvi.ProfileEffect
import com.example.homework_2.presentation.profile.me.mvi.ProfileEvent
import com.example.homework_2.presentation.profile.me.mvi.ProfileState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
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
        Log.d("ProfileViewModel", "ViewModel created")
    }

    fun loadProfile() {
        profileStore.accept(ProfileEvent.Ui.LoadDb)
    }

    fun updateProfile() {
        profileStore.accept(ProfileEvent.Ui.UpdateUser)
    }
}
