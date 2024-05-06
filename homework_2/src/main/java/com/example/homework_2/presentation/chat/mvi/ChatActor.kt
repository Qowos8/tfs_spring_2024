package com.example.homework_2.presentation.chat.mvi

import android.util.Log
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.event.NarrowItem
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.use_case.chat.DeleteReactionUseCase
import com.example.homework_2.domain.use_case.chat.GetMessagesUseCase
import com.example.homework_2.domain.use_case.chat.GetNextMessages
import com.example.homework_2.domain.use_case.chat.RegisterEventUseCase
import com.example.homework_2.domain.use_case.chat.SendMessageUseCase
import com.example.homework_2.domain.use_case.chat.SendReactionUseCase
import com.example.homework_2.domain.use_case.chat.TrackEventUseCase
import com.example.homework_2.domain.use_case.chat.UpdateMessagesUseCase
import com.example.homework_2.utils.runCatchingNonCancellation
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
    private val getNextMessages: GetNextMessages,
) : Actor<ChatCommand, ChatEvent>() {

    private var currentId = ""
    private var streamId: Int = 0
    private var topicName: String = ""

    private var allMessages: MutableList<MessageItem> = mutableListOf()
    private var previousResponse: MutableList<MessageItem>? = null

    override fun execute(command: ChatCommand): Flow<ChatEvent> {
        return when (command) {
            is ChatCommand.RegisterEvent -> flow { registerEvent(this) }

            is ChatCommand.DeleteReaction -> deleteReaction(command.emojiName, command.messageId)

            is ChatCommand.AddReaction -> sendReaction(command.emojiName, command.messageId)

            is ChatCommand.SendMessage -> sendMessage(
                command.streamName,
                command.topicName,
                command.content
            )

            is ChatCommand.GetDBMessages -> getMessages(command.streamId, command.topicName)
            is ChatCommand.UpdateMessages -> updateMessages(
                streamName = command.streamName,
                topicName = command.topicName,
                streamId = command.streamId,
                nextCount = command.nextCount)
        }
    }

    private fun getMessages(streamId: Int, topicName: String): Flow<ChatEvent> {
        return getMessagesUseCase(streamId, topicName)
            .mapEvents(
                eventMapper = {
                    this.streamId = streamId
                    this.topicName = topicName
                    if (it.isNotEmpty()){
                        ChatEvent.Domain.CacheSuccess(it)
                    }
                    else{
                        ChatEvent.Domain.CacheEmpty
                    }
                },
                errorMapper = {
                    ChatEvent.Domain.Error(it.message.toString())
                }
            )
    }

    private fun updateMessages(
        topicName: String,
        streamName: String,
        streamId: Int,
        nextCount: Int
    ): Flow<ChatEvent> {
        return flow {
            val narrow = Json.encodeToString(
                listOf(
                    NarrowItem(streamName, STREAM),
                    NarrowItem(topicName, TOPIC)
                )
            )
            runCatchingNonCancellation {
                updateMessagesUseCase.invoke(narrow, streamId, topicName, nextCount)
            }.onSuccess {
                emit(it)
            }.onFailure {
                throw Exception(it.printStackTrace().toString())
            }
        }.mapEvents (
            eventMapper = { oldMessages ->
                if (oldMessages.isNotEmpty()) {
                    ChatEvent.Domain.UpdateSuccess(oldMessages)
                }
                else{
                    ChatEvent.Domain.CacheLoaded
                }
            },
            errorMapper = {
                ChatEvent.Domain.Error(it.message.toString() + "updateMessages")
            }
        )
    }

    private fun sendMessage(
        streamName: String,
        topicName: String,
        content: String,
    ): Flow<ChatEvent> {
        return flow {
            emit(sendMessageUseCase(streamName, topicName, content))
        }.mapEvents(
            errorMapper = {
                ChatEvent.Domain.Error(it.message.toString())
            }
        )
    }

    private fun sendReaction(
        emojiName: String,
        messageId: Int,
    ): Flow<ChatEvent> {
        return flow {
            emit(sendReactionUseCase(emojiName, messageId))
        }.mapEvents(
            errorMapper = {
                ChatEvent.Domain.Error(it.message.toString())
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
                ChatEvent.Domain.Error(it.message.toString())
            }
        )
    }

    private suspend fun registerEvent(
        flowCollector: FlowCollector<ChatEvent>,
    ) {
        runCatchingNonCancellation {
            registerEventUseCase(
                fetchEventTypes = Json.encodeToString(TYPES),
                eventTypes = Json.encodeToString(TYPES)
            )
        }.onSuccess {
            currentId = it.id
            trackEvent(flowCollector)
        }.onFailure {
            flowCollector.emit(ChatEvent.Domain.Error(it.message.toString() + "registerEvent"))
        }
    }

    private suspend fun trackEvent(flowCollector: FlowCollector<ChatEvent>) {
        runCatchingNonCancellation {
            trackEventUseCase(currentId, TIMEOUT)
        }.onSuccess { eventResponse ->
            for (event in eventResponse.events) {
                getNextMessages.invoke(streamId, topicName, event.message!!.toDomain())
                flowCollector.emit(ChatEvent.Domain.CacheSuccess(allMessages))
            }
            previousResponse = allMessages
            registerEvent(flowCollector)
        }.onFailure { error ->
            registerEvent(flowCollector)
            flowCollector.emit(ChatEvent.Domain.Error(error.message.toString() + "trackEvent"))
        }
    }

    private companion object {
        private const val STREAM = "stream"
        private const val TOPIC = "topic"
        private const val MESSAGE = "message"
        private const val EMOJI = "emoji_realm"
        private const val TIMEOUT = 10000
        private val TYPES = listOf(MESSAGE, EMOJI)
    }
}
