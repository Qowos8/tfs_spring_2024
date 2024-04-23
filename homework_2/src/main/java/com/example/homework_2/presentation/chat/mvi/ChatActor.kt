package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.data.network.api.chat.ChatApi
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.event.NarrowItem
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import vivid.money.elmslie.core.store.Actor

class ChatActor : Actor<ChatCommand, ChatEvent>() {

    private val retrofit = RetrofitModule.create(ChatApi::class.java)
    private var currentId = ""
    private var lastEventId = 0

    private var allMessages: MutableList<MessageItem> = mutableListOf()
    private var previousResponse: List<MessageItem>? = null

    override fun execute(command: ChatCommand): Flow<ChatEvent> {
        return when (command) {
            is ChatCommand.RegisterEvent -> flow {
                registerEvent(this)
            }

            is ChatCommand.DeleteReaction -> flow {
                deleteReaction(this, command.emojiName, command.messageId)
            }

            is ChatCommand.AddReaction -> flow {
                sendReaction(this, command.emojiName, command.messageId)
            }

            is ChatCommand.SendMessage -> flow {
                sendMessage(this, command.streamName, command.topicName, command.content)
            }

            is ChatCommand.GetMessages -> flow {
                getMessages(this, command.streamName, command.topicName)
            }
        }
    }

    private suspend fun getMessages(
        flowCollector: FlowCollector<ChatEvent>,
        topicName: String,
        streamName: String,
    ) {
        runCatchingNonCancellation {
            val narrow = listOf(NarrowItem(topicName, STREAM), NarrowItem(streamName, TOPIC))
            retrofit.getTopicMessages(narrow = Json.encodeToString(narrow))
        }.onSuccess { response ->
            allMessages = response.messages.map { it.toDomain() }.toMutableList()
            if (response.messages != previousResponse) {
                if (response.messages != previousResponse) {
                    flowCollector.emit(ChatEvent.Domain.Success(response.messages.map { it.toDomain() }))
                }
            }
            previousResponse = response.messages.map { it.toDomain() }
        }.onFailure {
            flowCollector.emit(ChatEvent.Domain.Error(it.message.toString() + "getMessages"))
        }

    }

    private suspend fun sendMessage(
        flowCollector: FlowCollector<ChatEvent>,
        streamName: String,
        topicName: String,
        content: String,
    ) {
        runCatchingNonCancellation {
            retrofit.sendMessage(
                stream = streamName,
                topic = topicName,
                message = content
            )
        }.onFailure {
            flowCollector.emit(ChatEvent.Domain.Error(it.message.toString() + "sendMessage"))
        }
    }

    private suspend fun sendReaction(
        flowCollector: FlowCollector<ChatEvent>,
        emojiName: String,
        messageId: Int,
    ) {
        runCatchingNonCancellation {
            retrofit.addEmoji(messageId, emojiName)
        }.onFailure {
            flowCollector.emit(ChatEvent.Domain.Error(it.message.toString() + "sendReaction"))
        }
    }

    private suspend fun deleteReaction(
        flowCollector: FlowCollector<ChatEvent>,
        emojiName: String,
        messageId: Int,
    ) {
        runCatchingNonCancellation {
            RetrofitModule.create(ChatApi::class.java).deleteEmoji(messageId, emojiName)
        }.onFailure {
            flowCollector.emit(ChatEvent.Domain.Error(it.message.toString() + "deleteReaction"))
        }
    }

    private suspend fun registerEvent(
        flowCollector: FlowCollector<ChatEvent>,
    ) {
        runCatchingNonCancellation {
            retrofit.registerEvent(
                fetchEventTypes = Json.encodeToString(TYPES),
                eventTypes = Json.encodeToString(TYPES)
            )
        }.onSuccess {
            currentId = it.id
            lastEventId = it.lastEventId
            trackEvent(flowCollector)
        }.onFailure {
            flowCollector.emit(ChatEvent.Domain.Error(it.message.toString() + "registerEvent"))
        }
    }

    private suspend fun trackEvent(flowCollector: FlowCollector<ChatEvent>) {
        runCatchingNonCancellation {
            retrofit.trackEvent(currentId, lastEventId, TIMEOUT)
        }.onSuccess { eventResponse ->
            val newEvents = eventResponse.events.filter { it.type == MESSAGE || it.type == EMOJI }
            if (newEvents.isNotEmpty()) {
                for (event in newEvents) {
                    if (previousResponse?.any { it.id == event.message?.id } == false) {
                        allMessages.add(event.message!!.toDomain())
                        flowCollector.emit(ChatEvent.Domain.Success(allMessages))
                    }
                }
                previousResponse = allMessages
            }
            registerEvent(flowCollector)
        }.onFailure { error ->
            registerEvent(flowCollector)
            flowCollector.emit(ChatEvent.Domain.Error(error.message.toString() + "trackEvent"))
            error.printStackTrace()
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
