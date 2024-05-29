package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.event.NarrowItem
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.use_case.chat.DeleteReactionUseCase
import com.example.homework_2.domain.use_case.chat.GetMessagesUseCase
import com.example.homework_2.domain.use_case.chat.GetNextMessagesUseCase
import com.example.homework_2.domain.use_case.chat.RegisterEventUseCase
import com.example.homework_2.domain.use_case.chat.SendMessageUseCase
import com.example.homework_2.domain.use_case.chat.SendReactionUseCase
import com.example.homework_2.domain.use_case.chat.TrackEventUseCase
import com.example.homework_2.domain.use_case.chat.UpdateMessagesUseCase
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ChatActor @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val sendReactionUseCase: SendReactionUseCase,
    private val deleteReactionUseCase: DeleteReactionUseCase,
    private val registerEventUseCase: RegisterEventUseCase,
    private val trackEventUseCase: TrackEventUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val updateMessagesUseCase: UpdateMessagesUseCase,
    private val getNextMessagesUseCase: GetNextMessagesUseCase,
) : Actor<ChatCommand, ChatEvent>() {
    private var allMessages: MutableList<MessageItem> = mutableListOf()

    override fun execute(command: ChatCommand): Flow<ChatEvent> {
        return when (command) {
            is ChatCommand.RegisterEvent -> flow {
                registerEvent(this, command.state)
            }

            is ChatCommand.DeleteReaction -> deleteReaction(command.emojiName, command.messageId)

            is ChatCommand.AddReaction -> sendReaction(command.emojiName, command.messageId)

            is ChatCommand.SendMessage -> sendMessage(command.state, command.content)

            is ChatCommand.GetDBMessages -> getMessages(command.state)

            is ChatCommand.UpdateMessages -> updateMessages(
                command.state,
                command.nextCount
            )
        }
    }

    private fun getMessages(
        state: ChatHolderState,
    ): Flow<ChatEvent> {
        return getMessagesUseCase(state.streamId, state.topicName)
            .mapEvents(
                eventMapper = {
                    if (it.isNotEmpty()) {
                        ChatEvent.Domain.CacheSuccess(it)
                    } else {
                        ChatEvent.Domain.CacheEmpty
                    }
                },
                errorMapper = {
                    ChatEvent.Domain.Error(NETWORK_ERROR)
                }
            )
    }

    private fun updateMessages(
        state: ChatHolderState,
        nextCount: Int,
    ): Flow<ChatEvent> {
        return flow {
            val narrow = Json.encodeToString(
                listOf(
                    NarrowItem(state.streamName, STREAM),
                    NarrowItem(state.topicName, TOPIC)
                )
            )
            runCatchingNonCancellation {
                updateMessagesUseCase.invoke(
                    narrow, state.streamId, state.topicName, nextCount
                )
            }.onSuccess {
                emit(it)
            }
        }.mapEvents(
            eventMapper = { oldMessages ->
                if (oldMessages.isNotEmpty()) {
                    ChatEvent.Domain.UpdateSuccess(oldMessages)
                } else {
                    ChatEvent.Domain.CacheLoaded
                }
            },
            errorMapper = {
                ChatEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }

    private fun sendMessage(
        state: ChatHolderState,
        content: String,
    ): Flow<ChatEvent> {
        return flow {
            emit(
                sendMessageUseCase(
                    state.streamName,
                    state.topicName, content
                )
            )
        }.mapEvents(
            errorMapper = {
                ChatEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }

    private fun sendReaction(
        emojiName: String,
        messageId: Int,
    ): Flow<ChatEvent> {
        return flow {
            emit(
                sendReactionUseCase(
                    emojiName = emojiName,
                    messageId = messageId
                )
            )
        }.mapEvents(
            errorMapper = {
                ChatEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }

    private fun deleteReaction(
        emojiName: String,
        messageId: Int,
    ): Flow<ChatEvent> {
        return flow {
            emit(deleteReactionUseCase(emojiName, messageId))
        }.mapEvents(
            errorMapper = {
                ChatEvent.Domain.Error(NETWORK_ERROR)
            }
        )
    }

    private suspend fun registerEvent(
        flowCollector: FlowCollector<ChatEvent>,
        state: ChatHolderState,
    ) {
        runCatchingNonCancellation {
            registerEventUseCase(
                fetchEventTypes = Json.encodeToString(TYPES),
                eventTypes = Json.encodeToString(TYPES)
            )
        }.onSuccess {
            trackEvent(flowCollector, it.id, state)
        }.onFailure {
            delay(2000)
            registerEvent(flowCollector, state)
        }
    }

    private suspend fun trackEvent(
        flowCollector: FlowCollector<ChatEvent>,
        registerId: String,
        state: ChatHolderState,
    ) {
        runCatchingNonCancellation {
            trackEventUseCase(registerId, TIMEOUT)
        }.onSuccess { eventResponse ->
            for (event in eventResponse.events) {
                getNextMessagesUseCase.invoke(
                    state.streamId, state.topicName, event.message!!.toDomain()
                )
                flowCollector.emit(ChatEvent.Domain.CacheSuccess(allMessages))
            }
            registerEvent(flowCollector, state)
        }.onFailure { error ->
            registerEvent(flowCollector, state)
            flowCollector.emit(ChatEvent.Domain.Error(NETWORK_ERROR))
        }
    }

    private companion object {
        private const val NETWORK_ERROR = "Network error"
        private const val STREAM = "stream"
        private const val TOPIC = "topic"
        private const val MESSAGE = "message"
        private const val EMOJI = "emoji_realm"
        private const val TIMEOUT = 10000
        private val TYPES = listOf(MESSAGE, EMOJI)
    }
}
