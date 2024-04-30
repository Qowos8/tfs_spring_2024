package com.example.homework_2.domain.repository

import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.domain.entity.MessageItem

interface ChatRepository {
    suspend fun sendMessage(streamName: String, topicName: String, content: String)

    suspend fun sendReaction(emojiName: String, messageId: Int)

    suspend fun deleteReaction(emojiName: String, messageId: Int)

    suspend fun registerEvent(fetchEventTypes: String, eventTypes: String): RegisterResponse

    suspend fun trackEvent(currentId: String, timeOut: Int): Events

    suspend fun getMessages(narrow: String): List<MessageItem>
}