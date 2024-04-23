package com.example.homework_2.presentation.people.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class PeopleReducer : ScreenDslReducer<
        PeopleEvent, PeopleEvent.Ui,
        PeopleEvent.Domain, PeopleState,
        PeopleEffect,
        PeopleCommand>(
    PeopleEvent.Ui::class,
    PeopleEvent.Domain::class
) {
    override fun Result.internal(event: PeopleEvent.Domain) {
        when (event) {
            PeopleEvent.Domain.Loading -> state { PeopleState.Loading }

            is PeopleEvent.Domain.Error -> state { PeopleState.Error(event.error) }

            is PeopleEvent.Domain.Success -> state { PeopleState.Success(event.value) }
        }
    }

    override fun Result.ui(event: PeopleEvent.Ui) = when (event) {
        PeopleEvent.Ui.Init -> commands { +PeopleCommand.LoadPeople }
    }
}