package com.example.homework_2.data.repository_impl

import com.example.homework_2.data.db.dao.MessageDao
import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.data.db.mapper.domain.toDomain
import com.example.homework_2.data.network.api.chat.ChatApi
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val api: ChatApi,
    private val dao: MessageDao,
) : ChatRepository {

    override suspend fun sendMessage(streamName: String, topicName: String, content: String) {
        api.sendMessage(
            message = content,
            stream = streamName,
            topic = topicName
        )
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

    override suspend fun registerEvent(
        fetchEventTypes: String,
        eventTypes: String,
    ): RegisterResponse {
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

    override suspend fun updateMessages(
        narrow: String,
        streamId: Int,
        topicName: String,
        nextCount: Int,
    ): List<MessageItem> {
        val count = dao.getTopicsMessageCount(streamId, topicName)
        var oldMessages: List<MessageItem> = emptyList()
        if (count < 50) {
            if (count <= DB_LIMIT || count + DB_LIMIT < 50) {
                dao.insertMessagesWithLimit(
                    api.getTopicMessages(narrow = narrow, numBefore = nextCount).messages
                        .map { it.toDB(streamId) }
                        .take(DB_LIMIT)
                )
            } else if (count + DB_LIMIT > 50) {
                dao.insertMessagesWithLimit(
                    api.getTopicMessages(narrow = narrow, numBefore = nextCount).messages
                        .map { it.toDB(streamId) }
                        .take(count + DB_LIMIT - 50)
                )
            }
        } else {
            oldMessages = api.getTopicMessages(
                narrow = narrow,
                numBefore = nextCount
            ).messages.map { it.toDomain() }
        }
        return oldMessages
    }

    override fun getMessages(streamId: Int, topicName: String): Flow<List<MessageItem>> {
        return dao.getAll(streamId, topicName).map { it.toDomain() }
    }

    override suspend fun getNextMessage(
        streamId: Int,
        topicName: String,
        newMessageItem: MessageItem,
    ) {
        val oldestMessage = dao.getOldestMessage(streamId, topicName)
        dao.deleteOldestMessage(oldestMessage)
        dao.insertMessage(newMessageItem.toDB(streamId))
    }

    private companion object {
        private const val DB_LIMIT = 20
    }
}