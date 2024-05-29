package com.example.homework_2.presentation.profile.me.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer
import javax.inject.Inject

class ProfileReducer @Inject constructor() : ScreenDslReducer<
        ProfileEvent,
        ProfileEvent.Ui,
        ProfileEvent.Domain,
        ProfileState,
        ProfileEffect,
        ProfileCommand>(ProfileEvent.Ui::class, ProfileEvent.Domain::class) {

    override fun Result.internal(event: ProfileEvent.Domain) {
        when (event) {
            is ProfileEvent.Domain.Error -> state { ProfileState.Error(event.error) }
            is ProfileEvent.Domain.CacheEmpty -> state { ProfileState.CacheEmpty }
            is ProfileEvent.Domain.CacheSuccess -> state { ProfileState.CacheSuccess(event.value)}
            is ProfileEvent.Domain.CacheLoaded -> state { ProfileState.CacheLoaded }
        }
    }

    override fun Result.ui(event: ProfileEvent.Ui) = when (event) {
        ProfileEvent.Ui.LoadDb -> {
            commands { +ProfileCommand.Init }
        }
        ProfileEvent.Ui.UpdateUser -> {
            commands { +ProfileCommand.LoadUser }
        }
    }
}