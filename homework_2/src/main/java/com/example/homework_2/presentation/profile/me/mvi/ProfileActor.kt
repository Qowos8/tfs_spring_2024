package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.domain.use_case.profile.GetProfileUseCase
import com.example.homework_2.domain.use_case.profile.UpdateProfileUseCase
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
        return getProfileUseCase()
            .mapEvents(
                eventMapper = {
                    ProfileEvent.Domain.CacheSuccess(it)
                },
                errorMapper = {
                    ProfileEvent.Domain.CacheEmpty
                }
            ).flowOn(Dispatchers.IO)
    }

    private fun updateProfile(): Flow<ProfileEvent> {
        return flow {
            runCatchingNonCancellation {
                emit(updateProfileUseCase())
            }
        }.mapEvents(
            eventMapper = {
                ProfileEvent.Domain.CacheLoaded
            },
            errorMapper = {
                ProfileEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }


    private companion object {
        private const val NETWORK_ERROR = "Network error"
    }
}
