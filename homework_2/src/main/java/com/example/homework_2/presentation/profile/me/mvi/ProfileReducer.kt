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
            is ProfileEvent.Domain.Success -> state { ProfileState.Success(event.value) }
            ProfileEvent.Domain.CacheEmpty -> state { ProfileState.Loading }
            is ProfileEvent.Domain.CacheSuccess -> state { ProfileState.CacheSuccess(event.value)}
        }
    }

    override fun Result.ui(event: ProfileEvent.Ui) = when (event) {
        ProfileEvent.Ui.Init -> commands { +ProfileCommand.Init }
        ProfileEvent.Ui.LoadUser -> commands { +ProfileCommand.LoadUser }
    }
}