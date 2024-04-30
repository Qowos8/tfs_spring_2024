package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.GetStreamTopicUseCase
import com.example.homework_2.domain.use_case.channels.GetSubStreamsUseCase
import com.example.homework_2.domain.use_case.channels.StreamSearchUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ChildActor @Inject constructor(
    private val getAllStreamsUseCase: GetAllStreamsUseCase,
    private val getSubStreamsUseCase: GetSubStreamsUseCase,
    private val searchUseCase: StreamSearchUseCase,
    private val getStreamTopicsUseCase: GetStreamTopicUseCase,
) : Actor<ChildCommand, ChildEvent>() {

    @Volatile
    private var allItems: List<StreamItem> = listOf()

    override fun execute(command: ChildCommand): Flow<ChildEvent> {
        return when (command) {
            is ChildCommand.LoadAllStream -> onLoadAllStream()
            is ChildCommand.LoadSubStream -> emitSub()
            is ChildCommand.LoadTopic -> onGetTopics(command.streamId)
            is ChildCommand.SearchStream -> onSearch(command.query)
        }
    }

    private fun onLoadAllStream(): Flow<ChildEvent> {
        return flow {
            emit(getAllStreamsUseCase())
        }.mapEvents(
            eventMapper = { streams ->
                allItems = streams
                ChildEvent.Domain.All.Success(allItems)
            },
            errorMapper = {
                ChildEvent.Domain.All.Error(it.message.toString())
            }
        )
    }

    private fun emitSub(): Flow<ChildEvent> {
        return flow {
            emit(getSubStreamsUseCase())
        }.mapEvents(
            eventMapper = { streams ->
                allItems = streams
                ChildEvent.Domain.Sub.Success(streams)
            },
            errorMapper = {
                ChildEvent.Domain.Sub.Error(it.message.toString())
            }
        )
    }

    private fun onGetTopics(streamId: Int): Flow<ChildEvent> {
        return flow {
            emit(getStreamTopicsUseCase(streamId))
        }.mapEvents(
            eventMapper = { topics ->
                ChildEvent.Domain.Topic.Success(topics)
            },
            errorMapper = {
                ChildEvent.Domain.Topic.Error(it.message.toString())
            }
        )
    }

    private fun onSearch(query: String): Flow<ChildEvent> {
        return flow {
            when (query.length) {
                0 -> emit(allItems)
                in 1..SEARCH_QUERY_LENGTH_LIMIT -> emit(searchUseCase(query, allItems))
                else -> throw IllegalArgumentException("too many symbols")
            }
        }.mapEvents(
            eventMapper = {
                ChildEvent.Domain.All.Success(it)
            },
            errorMapper = {
                (ChildEvent.Domain.All.Error("Too much symbols"))
            }
        )
            .onStart {
                emit(ChildEvent.Domain.All.Loading)
            }
    }

    private companion object {
        const val SEARCH_QUERY_LENGTH_LIMIT = 8
    }
}