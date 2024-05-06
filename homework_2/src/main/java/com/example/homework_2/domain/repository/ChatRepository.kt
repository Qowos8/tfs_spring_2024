package com.example.homework_2.domain.repository

import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.domain.entity.MessageItem
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(streamName: String, topicName: String, content: String)

    suspend fun sendReaction(emojiName: String, messageId: Int)

    suspend fun deleteReaction(emojiName: String, messageId: Int)

    suspend fun registerEvent(fetchEventTypes: String, eventTypes: String): RegisterResponse

    suspend fun trackEvent(currentId: String, timeOut: Int): Events

    suspend fun updateMessages(narrow: String, streamId: Int, topicName: String, nextCount: Int): List<MessageItem>

    fun getMessages(streamId: Int, topicName: String): Flow<List<MessageItem>>

    suspend fun getNextMessage(streamId: Int, topicName: String, newMessageItem: MessageItem)
}