package com.example.homework_2.presentation.people.mvi

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.use_case.people.GetPeopleUseCase
import com.example.homework_2.domain.use_case.people.SearchUsersUseCase
import com.example.homework_2.domain.use_case.people.UpdatePeopleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class PeopleActor @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val updatePeopleUseCase: UpdatePeopleUseCase,
    private val searchUsersUseCase: SearchUsersUseCase,
) : Actor<PeopleCommand, PeopleEvent>() {

    @Volatile
    private var allItems: List<ProfileItem> = listOf()

    override fun execute(command: PeopleCommand): Flow<PeopleEvent> {
        return when (command) {
            is PeopleCommand.LoadDbPeople -> {
                getPeople()
            }

            is PeopleCommand.UpdatePeople -> {
                updatePeople()
            }

            is PeopleCommand.SearchUsers -> {
                searchUsers(command.query)
            }
        }
    }

    private fun updatePeople(): Flow<PeopleEvent> {
        return flow<Unit> {
            updatePeopleUseCase()
        }.mapEvents(
            errorMapper = {
                PeopleEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }

    private fun getPeople(): Flow<PeopleEvent> {
        return getPeopleUseCase().mapEvents(
            eventMapper = { users ->
                allItems = users
                PeopleEvent.Domain.CacheSuccess(users)
            },
            errorMapper = {
                PeopleEvent.Domain.Error(it.message.toString())
            }
        )
    }

    private fun searchUsers(query: String): Flow<PeopleEvent> {
        return flow {
            when (query.length) {
                0 -> emit(allItems)
                in 1..SEARCH_QUERY_LENGTH_LIMIT -> emit(searchUsersUseCase(query, allItems))
                else -> throw IllegalArgumentException(SEARCH_ERROR)
            }
        }.mapEvents(
            eventMapper = {
                PeopleEvent.Domain.CacheSuccess(it)
            },
            errorMapper = {
                (PeopleEvent.Domain.Error(SEARCH_ERROR))
            }
        )
    }


    private companion object {
        const val SEARCH_QUERY_LENGTH_LIMIT = 8
        private const val NETWORK_ERROR = "Network error"
        private const val SEARCH_ERROR = "Too much symbols"
    }
}