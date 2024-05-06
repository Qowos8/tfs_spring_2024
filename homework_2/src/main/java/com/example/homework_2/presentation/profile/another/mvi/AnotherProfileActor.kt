package com.example.homework_2.presentation.profile.another.mvi

import com.example.homework_2.domain.use_case.profile.GetAnotherProfileUseCase
import com.example.homework_2.domain.use_case.profile.UpdateAnotherProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class AnotherProfileActor @Inject constructor(
    private val updateAnotherProfileUseCase: UpdateAnotherProfileUseCase,
    private val getAnotherProfileUseCase: GetAnotherProfileUseCase
) : Actor<AnotherProfileCommand, AnotherProfileEvent>() {

    override fun execute(command: AnotherProfileCommand): Flow<AnotherProfileEvent> {
        return when (command) {
            is AnotherProfileCommand.Init -> { getPeople(command.userId) }
            is AnotherProfileCommand.LoadUser -> { updatePeople(command.userId) }
        }
    }

    private fun updatePeople(userId: Int): Flow<AnotherProfileEvent>{
        return flow<Unit> {
            updateAnotherProfileUseCase.invoke(userId)
        }.mapEvents(
            errorMapper = {
                AnotherProfileEvent.Domain.Error(it.message.toString())
            }
        )
    }

    private fun getPeople(userId: Int): Flow<AnotherProfileEvent>{
        return getAnotherProfileUseCase.invoke(userId).mapEvents(
            eventMapper = {
                AnotherProfileEvent.Domain.CacheSuccess(it)
            },
            errorMapper = {
                AnotherProfileEvent.Domain.CacheEmpty
            }
        )
    }
}