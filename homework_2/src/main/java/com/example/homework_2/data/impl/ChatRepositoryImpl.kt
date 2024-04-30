package com.example.homework_2.data.impl

import com.example.homework_2.data.network.api.chat.ChatApi
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val api: ChatApi
): ChatRepository {

    override suspend fun sendMessage(streamName: String, topicName: String, content: String) {
        api.sendMessage(
            message = content,
            stream = streamName,
            topic = topicName)
    }

    override suspend fun sendReaction(emojiName: String, messageId: Int) {
        api.addEmoji(
            messageId = messageId,
            emoji_name = emojiName
        )
    }

    override suspend fun deleteReaction(emojiName: String, messageId: Int) {
        api.deleteEmoji(
            messageId = messageId,
            emoji_name = emojiName
        )
    }

    override suspend fun registerEvent(fetchEventTypes: String, eventTypes: String): RegisterResponse {
        return api.registerEvent(
            fetchEventTypes = fetchEventTypes,
            eventTypes = eventTypes
        )
    }

    override suspend fun trackEvent(currentId: String, timeOut: Int): Events {
        return api.trackEvent(
            queueId = currentId,
            timeout = timeOut
        )
    }

    override suspend fun getMessages(narrow: String): List<MessageItem> {
        return api.getTopicMessages(
            narrow = narrow
        ).messages.map { it.toDomain() }
    }
}