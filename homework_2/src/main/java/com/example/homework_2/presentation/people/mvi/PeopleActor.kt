package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.data.network.api.profile.ProfileApi
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor

class PeopleActor : Actor<PeopleCommand, PeopleEvent>() {

    override fun execute(command: PeopleCommand): Flow<PeopleEvent> {
        return when (command) {
            is PeopleCommand.LoadPeople -> flow {
                runCatchingNonCancellation {
                    RetrofitModule.create(ProfileApi::class.java).getUsers().members
                }.onSuccess { users ->
                    emit(PeopleEvent.Domain.Success(users.map { it.toDomain() }))
                }.onFailure {
                    emit(PeopleEvent.Domain.Error(it.message.toString()))
                }
            }
        }
    }
}