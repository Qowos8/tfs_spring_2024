package com.example.homework_2.presentation.channels.child.mvi

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.use_case.channels.CreateChannelUseCase
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.GetSubStreamUseCase
import com.example.homework_2.domain.use_case.channels.GetTopicUseCase
import com.example.homework_2.domain.use_case.channels.StreamSearchUseCase
import com.example.homework_2.domain.use_case.channels.UpdateAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.UpdateSubStreamUseCase
import com.example.homework_2.domain.use_case.channels.UpdateTopicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ChannelsActor @Inject constructor(
    private val searchUseCase: StreamSearchUseCase,
    private val getAllStreamsUseCase: GetAllStreamsUseCase,
    private val getSubStreamUseCase: GetSubStreamUseCase,
    private val getTopicUseCase: GetTopicUseCase,
    private val updateTopicUseCase: UpdateTopicUseCase,
    private val updateAllStreamUseCase: UpdateAllStreamsUseCase,
    private val updateSubStreamUseCase: UpdateSubStreamUseCase,
    private val createChannelUseCase: CreateChannelUseCase,
) : Actor<ChannelsCommand, ChannelsEvent>() {

    @Volatile
    private var allItems: List<StreamItem> = listOf()
    private var isSub: Boolean? = null

    override fun execute(command: ChannelsCommand): Flow<ChannelsEvent> {
        return when (command) {
            is ChannelsCommand.SearchStream -> onSearch(command.query)

            is ChannelsCommand.LoadAllStream -> updateAllStream()
            is ChannelsCommand.LoadSubStream -> updateSubStream()
            is ChannelsCommand.LoadTopic -> updateTopics(command.streamId)
            is ChannelsCommand.CreateStream -> createChannel(command.name, command.description)

            is ChannelsCommand.LoadDBAllStream -> getAllStreams()
            is ChannelsCommand.LoadDBSubStream -> getSubStreams()
            is ChannelsCommand.LoadDBTopics -> getTopics(command.streamId)

        }
    }

    private fun updateAllStream(): Flow<ChannelsEvent> {
        return flow<Unit> {
            updateAllStreamUseCase()
        }.mapEvents(
            errorMapper = {
                ChannelsEvent.Domain.All.Error(ERROR_NETWORK)
            }
        )
    }

    private fun updateSubStream(): Flow<ChannelsEvent> {
        return flow<Unit> {
            updateSubStreamUseCase()
        }.mapEvents(
            errorMapper = {
                ChannelsEvent.Domain.Sub.Error(ERROR_NETWORK)
            }
        )
    }

    private fun updateTopics(streamId: Int): Flow<ChannelsEvent> {
        return flow<Unit> {
            updateTopicUseCase(streamId)
        }.mapEvents(
            eventMapper = {
                ChannelsEvent.Domain.Topic.CacheLoaded
            },
            errorMapper = {
                ChannelsEvent.Domain.Topic.Error(ERROR_NETWORK)
            }
        )
    }

    private fun onSearch(query: String): Flow<ChannelsEvent> {
        return flow {
            when (query.length) {
                0 -> emit(allItems)
                in 1..SEARCH_QUERY_LENGTH_LIMIT -> emit(searchUseCase(query, allItems))
                else -> throw IllegalArgumentException(SEARCH_ERROR)
            }
        }.mapEvents(
            eventMapper = {
                ChannelsEvent.Domain.All.CacheSuccess(it)
            },
            errorMapper = {
                (ChannelsEvent.Domain.All.Error(SEARCH_ERROR))
            }
        )
    }

    private fun getAllStreams(): Flow<ChannelsEvent> {
        return getAllStreamsUseCase()
            .distinctUntilChanged()
            .mapEvents(
                eventMapper = { streams ->
                    if (streams.isEmpty()) {
                        ChannelsEvent.Domain.All.CacheEmpty
                    } else {
                        allItems = streams
                        isSub = false
                        ChannelsEvent.Domain.All.CacheSuccess(streams)
                    }
                },
                errorMapper = {
                    ChannelsEvent.Domain.All.Error(ERROR_NETWORK)
                }
            ).flowOn(Dispatchers.IO)
    }

    private fun getSubStreams(): Flow<ChannelsEvent> {
        return getSubStreamUseCase()
            .distinctUntilChanged()
            .mapEvents(
                eventMapper = { streams ->
                    if (streams.isEmpty()) {
                        ChannelsEvent.Domain.Sub.CacheEmpty
                    } else {
                        allItems = streams
                        isSub = true
                        ChannelsEvent.Domain.Sub.CacheSuccess(streams)
                    }
                },
                errorMapper = {
                    ChannelsEvent.Domain.Sub.Error(ERROR_NETWORK)
                }
            ).flowOn(Dispatchers.IO)
    }

    private fun getTopics(streamId: Int): Flow<ChannelsEvent> {
        return getTopicUseCase(streamId)
            .distinctUntilChanged()
            .mapEvents(
                eventMapper = { topics ->
                    if (topics.isEmpty()) {
                        ChannelsEvent.Domain.Topic.CacheEmpty
                    } else {
                        ChannelsEvent.Domain.Topic.CacheSuccess(topics)
                    }
                },
                errorMapper = {
                    ChannelsEvent.Domain.Topic.Error(ERROR_NETWORK)
                }
            ).flowOn(Dispatchers.IO)
    }

    private fun createChannel(name: String, description: String): Flow<ChannelsEvent> {
        return flow {
            emit(
                createChannelUseCase(
                    name = name,
                    description = description
                )
            )
        }.mapEvents(
            eventMapper = {
                ChannelsEvent.Domain.Sub.CacheEmpty
            },
            errorMapper = {
                ChannelsEvent.Domain.ErrorCreate(ERROR_CREATING)
            }
        )
    }

    private companion object {
        const val SEARCH_QUERY_LENGTH_LIMIT = 8
        private const val ERROR_NETWORK = "Network error"
        private const val SEARCH_ERROR = "Too much symbols"
        private const val ERROR_CREATING = "Create channel error"
    }
}