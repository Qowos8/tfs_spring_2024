package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.GetSubStreamUseCase
import com.example.homework_2.domain.use_case.channels.GetTopicUseCase
import com.example.homework_2.domain.use_case.channels.UpdateAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.UpdateSubStreamUseCase
import com.example.homework_2.domain.use_case.channels.UpdateTopicUseCase
import com.example.homework_2.domain.use_case.channels.StreamSearchUseCase
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ChildActor @Inject constructor(
    private val searchUseCase: StreamSearchUseCase,
    private val getAllStreamsUseCase: GetAllStreamsUseCase,
    private val getSubStreamUseCase: GetSubStreamUseCase,
    private val getTopicUseCase: GetTopicUseCase,
    private val updateTopicUseCase: UpdateTopicUseCase,
    private val updateAllStreamUseCase: UpdateAllStreamsUseCase,
    private val updateSubStreamUseCase: UpdateSubStreamUseCase,
) : Actor<ChildCommand, ChildEvent>() {

    @Volatile
    private var allItems: List<StreamItem> = listOf()
    private var isSub: Boolean? = null

    override fun execute(command: ChildCommand): Flow<ChildEvent> {
        return when (command) {
            is ChildCommand.SearchStream -> onSearch(command.query)

            is ChildCommand.LoadAllStream -> updateAllStream()
            is ChildCommand.LoadSubStream -> updateSubStream()
            is ChildCommand.LoadTopic -> updateTopics(command.streamId)

            is ChildCommand.LoadDBAllStream -> getAllStreams()
            is ChildCommand.LoadDBSubStream -> getSubStreams()
            is ChildCommand.LoadDBTopics -> getTopics(command.streamId)
        }
    }

    private fun updateAllStream(): Flow<ChildEvent> {
        return flow<Unit> {
            updateAllStreamUseCase()
        }.mapEvents(
            errorMapper = {
                ChildEvent.Domain.All.Error(ERROR_NETWORK)
            }
        )
    }

    private fun updateSubStream(): Flow<ChildEvent> {
        return flow<Unit> {
            updateSubStreamUseCase()
        }.mapEvents(
            errorMapper = {
                ChildEvent.Domain.Sub.Error(ERROR_NETWORK)
            }
        )
    }

    private fun updateTopics(streamId: Int): Flow<ChildEvent> {
        return flow<Unit> {
            updateTopicUseCase(streamId)
        }.mapEvents(
            errorMapper = {
                ChildEvent.Domain.Topic.Error(ERROR_NETWORK)
            }
        )
    }

    private fun onSearch(query: String): Flow<ChildEvent> {
            return flow {
                when (query.length) {
                    0 -> emit(allItems)
                    in 1..SEARCH_QUERY_LENGTH_LIMIT -> emit(searchUseCase(query, allItems))
                    else -> throw IllegalArgumentException(SEARCH_ERROR)
                }
            }.mapEvents(
                eventMapper = {
                    ChildEvent.Domain.All.CacheSuccess(it)
                },
                errorMapper = {
                    (ChildEvent.Domain.All.Error(SEARCH_ERROR))
                }
            )
                .onStart {
                    if(isSub == true) emit(ChildEvent.Domain.Sub.Loading)
                    else emit(ChildEvent.Domain.All.Loading)
                }

    }

    private fun getAllStreams(): Flow<ChildEvent> {
        return getAllStreamsUseCase.invoke()
            .mapEvents(
                eventMapper = { streams ->
                    if (streams.isEmpty()) {
                        ChildEvent.Domain.All.CacheEmpty
                    } else {
                        allItems = streams
                        isSub = false
                        ChildEvent.Domain.All.CacheSuccess(streams)
                    }
                },
                errorMapper = {
                    ChildEvent.Domain.All.Error(ERROR_NETWORK)
                }
            )
    }

    private fun getSubStreams(): Flow<ChildEvent> {
        return getSubStreamUseCase.invoke()
            .mapEvents(
                eventMapper = { streams ->
                    if (streams.isEmpty()) {
                        ChildEvent.Domain.Sub.CacheEmpty
                    } else {
                        allItems = streams
                        isSub = true
                        ChildEvent.Domain.Sub.CacheSuccess(streams)
                    }
                },
                errorMapper = {
                    ChildEvent.Domain.Sub.Error(ERROR_NETWORK)
                }
            )
    }

    private fun getTopics(streamId: Int): Flow<ChildEvent> {
        return getTopicUseCase.invoke(streamId)
            .mapEvents(
                eventMapper = { topics ->
                    if (topics.isEmpty()) {
                        ChildEvent.Domain.Topic.CacheEmpty
                    } else {
                        ChildEvent.Domain.Topic.CacheSuccess(topics)
                    }
                },
                errorMapper = {
                    ChildEvent.Domain.Topic.Error(ERROR_NETWORK)
                }
            )
    }

    private companion object {
        const val SEARCH_QUERY_LENGTH_LIMIT = 8
        private const val ERROR_NETWORK = "Network error"
        private const val SEARCH_ERROR = "Too much symbols"
    }
}