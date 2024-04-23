package com.example.homework_2.presentation.profile.me.mvi

import com.example.homework_2.data.network.api.profile.ProfileApi
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor

class ProfileActor : Actor<ProfileCommand, ProfileEvent>() {

    override fun execute(command: ProfileCommand): Flow<ProfileEvent> {
        return when (command) {
            is ProfileCommand.LoadUser -> flow {
                runCatchingNonCancellation {
                    RetrofitModule.create(ProfileApi::class.java).getUserMe()
                }.onSuccess {
                    emit(ProfileEvent.Domain.Success(it.toDomain()))
                }.onFailure {
                    emit(ProfileEvent.Domain.Error(it.message.toString()))
                }
            }
        }
    }
}