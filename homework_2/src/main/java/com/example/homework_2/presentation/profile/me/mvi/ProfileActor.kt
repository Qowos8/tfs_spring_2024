package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.domain.use_case.profile.UpdateAnotherProfileUseCase
import com.example.homework_2.domain.use_case.profile.GetProfileUseCase
import com.example.homework_2.domain.use_case.profile.UpdateProfileUseCase
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ProfileActor @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : Actor<ProfileCommand, ProfileEvent>() {

    override fun execute(command: ProfileCommand): Flow<ProfileEvent> {
        return when (command) {
            is ProfileCommand.Init -> {
                getProfile()
            }

            is ProfileCommand.LoadUser -> {
                updateProfile()
            }
        }
    }

    private fun getProfile(): Flow<ProfileEvent> {
        return getProfileUseCase.invoke()
        .mapEvents(
            eventMapper = {
                ProfileEvent.Domain.Success(it)
            },
            errorMapper = {
                ProfileEvent.Domain.Error(it.message.toString())
            }
        )
    }

    private fun updateProfile(): Flow<ProfileEvent> {
        return flow<Unit> {
            updateProfileUseCase
        }.mapEvents(
            errorMapper = {
                ProfileEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }

    private companion object{
        private const val NETWORK_ERROR = "Network error"
    }
}
