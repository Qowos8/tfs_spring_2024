package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.domain.use_case.profile.ProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ProfileActor @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : Actor<ProfileCommand, ProfileEvent>() {

    override fun execute(command: ProfileCommand): Flow<ProfileEvent> {
        return when (command) {
            is ProfileCommand.LoadUser -> flow {
                emit(profileUseCase.invoke())
            }.mapEvents(
                eventMapper = {
                    ProfileEvent.Domain.Success(it)
                },
                errorMapper = {
                    ProfileEvent.Domain.Error(it.message.toString())
                }
            )
        }
    }
}
