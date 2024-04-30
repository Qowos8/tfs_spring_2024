package com.example.homework_2.presentation.profile.another.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer
import javax.inject.Inject

class AnotherProfileReducer @Inject constructor() : ScreenDslReducer<
        AnotherProfileEvent, AnotherProfileEvent.Ui,
        AnotherProfileEvent.Domain, AnotherProfileState,
        AnotherProfileEffect,
        AnotherProfileCommand>(
    AnotherProfileEvent.Ui::class,
    AnotherProfileEvent.Domain::class
) {
    override fun Result.internal(event: AnotherProfileEvent.Domain) {
        when (event) {
            AnotherProfileEvent.Domain.Loading -> state { AnotherProfileState.Loading }

            is AnotherProfileEvent.Domain.Error -> state { AnotherProfileState.Error(event.error) }

            is AnotherProfileEvent.Domain.Success -> state { AnotherProfileState.Success(event.value) }
        }
    }

    override fun Result.ui(event: AnotherProfileEvent.Ui) = when (event) {
        is AnotherProfileEvent.Ui.LoadUser -> commands { +AnotherProfileCommand.LoadUser(event.userId) }
    }
}