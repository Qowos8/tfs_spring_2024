package com.example.homework_2.presentation.profile.me.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class ProfileReducer : ScreenDslReducer<
        ProfileEvent, ProfileEvent.Ui,
        ProfileEvent.Domain, ProfileState,
        ProfileEffect,
        ProfileCommand>(
    ProfileEvent.Ui::class, ProfileEvent.Domain::class
) {
    override fun Result.internal(event: ProfileEvent.Domain) {
        when (event) {
            ProfileEvent.Domain.Loading -> state { ProfileState.Loading }

            is ProfileEvent.Domain.Error -> state { ProfileState.Error(event.error) }

            is ProfileEvent.Domain.Success -> state { ProfileState.Success(event.value) }
        }
    }

    override fun Result.ui(event: ProfileEvent.Ui) = when (event) {
        ProfileEvent.Ui.Init -> commands { +ProfileCommand.LoadUser }
    }
}