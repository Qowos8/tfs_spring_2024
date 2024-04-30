package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.domain.use_case.people.PeopleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class PeopleActor @Inject constructor(
    private val useCase: PeopleUseCase
) : Actor<PeopleCommand, PeopleEvent>() {

    override fun execute(command: PeopleCommand): Flow<PeopleEvent> {
        return when (command) {
            is PeopleCommand.LoadPeople -> flow {
                    emit(useCase.invoke())
            }.mapEvents(
                eventMapper = {
                    PeopleEvent.Domain.Success(it)
                },
                errorMapper = {
                    PeopleEvent.Domain.Error(it.message.toString())
                }
            )
        }
    }
}