package com.example.homework_2.presentation.profile.another.mvi

import com.example.homework_2.data.network.api.profile.ProfileApi
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor

class AnotherProfileActor : Actor<AnotherProfileCommand, AnotherProfileEvent>() {

    override fun execute(command: AnotherProfileCommand): Flow<AnotherProfileEvent> {
        return when (command) {
            is AnotherProfileCommand.LoadUser -> flow {
                runCatchingNonCancellation {
                    RetrofitModule.create(ProfileApi::class.java).getUser(command.userId).user
                }.onSuccess {
                    emit(AnotherProfileEvent.Domain.Success(it.toDomain()))
                }.onFailure {
                    emit(AnotherProfileEvent.Domain.Error(it.message.toString()))
                }
            }
        }
    }
}