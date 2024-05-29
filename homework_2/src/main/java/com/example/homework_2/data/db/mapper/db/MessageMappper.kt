package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.MessageDbItem
import com.example.homework_2.data.network.model.chat.message.MessageItemApi
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi
import com.example.homework_2.data.network.model.chat.reaction.ReactionUserApi
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.entity.ReactionItem
import com.example.homework_2.domain.entity.ReactionUser
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun MessageItemApi.toDB(streamId: Int): MessageDbItem {
    return MessageDbItem(
        id = id.toInt(),
        streamId = streamId,
        userId = userId?.toInt(),
        userFullName = userFullName,
        topicName = topicName,
        avatarUrl = avatarUrl,
        content = content,
        timestamp = timestamp,
        reactions = fromApiToJsonString(reactions)
    )
}


fun fromDomainToJsonString(reactions: List<ReactionItem?>?): String {
    val json = Json { ignoreUnknownKeys = true }
    return json.encodeToString(reactions)
}

fun fromApiToJsonString(reactions: List<ReactionItemApi?>?): String {
    val json = Json { ignoreUnknownKeys = true }
    return json.encodeToString(reactions)
}

fun MessageItem.toDB(streamId: Int): MessageDbItem {
    return MessageDbItem(
        id = id,
        streamId = streamId,
        userId = userId,
        userFullName = userFullName,
        topicName = topicName,
        avatarUrl = avatarUrl,
        content = content,
        timestamp = timestamp,
        reactions = fromDomainToJsonString(reactions)
    )
}