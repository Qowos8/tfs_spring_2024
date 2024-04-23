package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.data.network.api.channels.ChannelsApi
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.utils.FilterByNamesUtils
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor

class ChildActor : Actor<ChildCommand, ChildEvent>() {

    private val retrofit = RetrofitModule.create(ChannelsApi::class.java)
    private var allItems: List<StreamItem> = mutableListOf()

    override fun execute(command: ChildCommand): Flow<ChildEvent> {
        return when (command) {
            is ChildCommand.LoadAllStream -> emitAll()

            is ChildCommand.LoadSubStream -> emitSub()

            is ChildCommand.LoadTopic -> emitTopic(command.streamId)

            is ChildCommand.SearchStream -> { emitState(command.query) }
        }
    }

    private fun emitAll(): Flow<ChildEvent> {
        return flow {
            runCatchingNonCancellation {
                retrofit.getAllStreams().subscriptions
            }.onSuccess { stream ->
                emit(ChildEvent.Domain.All.Success(stream.map { it.toDomain() }))
                allItems = stream.map { it.toDomain() }
            }.onFailure {
                emit(ChildEvent.Domain.All.Error(it.message.toString()))
            }
        }
    }

    private fun emitSub(): Flow<ChildEvent> {
        return flow {
            runCatchingNonCancellation {
                retrofit.getSubStreams().subscriptions
            }.onSuccess { stream ->
                emit(ChildEvent.Domain.Sub.Success(stream.map { it.toDomain() }))
                allItems = stream.map { it.toDomain() }
            }.onFailure {
                emit(ChildEvent.Domain.Sub.Error(it.message.toString()))
            }
        }
    }

    private fun emitTopic(streamId: Int): Flow<ChildEvent> {
        return flow {
            runCatchingNonCancellation {
                retrofit.getTopics(streamId).topics
            }.onSuccess { topics ->
                emit(ChildEvent.Domain.Topic.Success(topics.map { it.toDomain() }))
            }.onFailure {
                emit(ChildEvent.Domain.Topic.Error(it.message.toString()))
            }
        }
    }

    private fun emitState(query: String): Flow<ChildEvent> {
        return flow {
            runCatchingNonCancellation {
                if (query.isEmpty()) {
                    emit(ChildEvent.Domain.All.Success(allItems))
                } else if (query.length > 5) {
                    emit(ChildEvent.Domain.All.Error("Too much symbols"))
                } else {
                    emit(ChildEvent.Domain.All.Loading)
                    delay(700L)
                    emit(ChildEvent.Domain.All.Success(FilterByNamesUtils.filterItemsByName(allItems, query)))
                }
            }
        }
    }
}