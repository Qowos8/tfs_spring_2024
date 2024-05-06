package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.domain.use_case.people.GetPeopleUseCase
import com.example.homework_2.domain.use_case.people.UpdatePeopleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class PeopleActor @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val updatePeopleUseCase: UpdatePeopleUseCase,
) : Actor<PeopleCommand, PeopleEvent>() {

    override fun execute(command: PeopleCommand): Flow<PeopleEvent> {
        return when (command) {
            is PeopleCommand.Init -> { getPeople() }
            is PeopleCommand.LoadPeople ->  { updatePeople() }
        }
    }

    private fun updatePeople(): Flow<PeopleEvent> {
        return flow<Unit> {
            updatePeopleUseCase()
        }.mapEvents(
            errorMapper = {
                PeopleEvent.Domain.Error("Network connection is out")
            }
        )
    }

    private fun getPeople(): Flow<PeopleEvent>{
        return getPeopleUseCase.invoke().mapEvents(
            eventMapper = { users ->
                PeopleEvent.Domain.CacheSuccess(users)
            },
            errorMapper = {
                PeopleEvent.Domain.Error(it.message.toString())
            }
        )
    }
}