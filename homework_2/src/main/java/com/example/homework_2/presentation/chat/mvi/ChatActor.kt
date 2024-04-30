package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.event.NarrowItem
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.use_case.chat.DeleteReactionUseCase
import com.example.homework_2.domain.use_case.chat.GetMessagesUseCase
import com.example.homework_2.domain.use_case.chat.RegisterEventUseCase
import com.example.homework_2.domain.use_case.chat.SendMessageUseCase
import com.example.homework_2.domain.use_case.chat.SendReactionUseCase
import com.example.homework_2.domain.use_case.chat.TrackEventUseCase
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
) : Actor<ChatCommand, ChatEvent>() {

    private var currentId = ""

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

            is ChatCommand.GetMessages -> getMessages(command.streamName, command.topicName)
        }
    }

    private fun getMessages(
        topicName: String,
        streamName: String,
    ): Flow<ChatEvent> {
        return flow {
            val narrow = Json.encodeToString(
                listOf(
                    NarrowItem(topicName, STREAM),
                    NarrowItem(streamName, TOPIC)
                )
            )
            emit(getMessagesUseCase(narrow = narrow))
        }.mapEvents(
            eventMapper = { messages ->
                allMessages = messages.toMutableList()
                previousResponse = messages.toMutableList()
                ChatEvent.Domain.Success(messages)
            },
            errorMapper = {
                ChatEvent.Domain.Error(it.message.toString())
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
                allMessages.add(event.message!!.toDomain())
                flowCollector.emit(ChatEvent.Domain.Success(allMessages))
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
