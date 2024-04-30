package com.example.homework_2.presentation.profile.another.mvi

import com.example.homework_2.domain.use_case.profile.AnotherProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class AnotherProfileActor @Inject constructor(
    private val profileUseCase: AnotherProfileUseCase
) : Actor<AnotherProfileCommand, AnotherProfileEvent>() {

    override fun execute(command: AnotherProfileCommand): Flow<AnotherProfileEvent> {
        return when (command) {
            is AnotherProfileCommand.LoadUser -> flow {
                emit(profileUseCase.invoke(command.userId))
            }.mapEvents(
                eventMapper = {
                    AnotherProfileEvent.Domain.Success(it)
                },
                errorMapper = {
                    AnotherProfileEvent.Domain.Error(it.message.toString())
                }
            )
        }
    }
}