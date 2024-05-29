package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.MessageDbItem
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.entity.ReactionItem
import kotlinx.serialization.json.Json

fun MessageDbItem.toDomain(): MessageItem {
    return MessageItem(
        id = id,
        userId = userId,
        userFullName = userFullName,
        topicName = topicName,
        avatarUrl = avatarUrl,
        content = content,
        reactions = fromJsonString(reactions).toMutableList(),
        timestamp = timestamp
    )
}

fun List<MessageDbItem>.toDomain(): List<MessageItem>{
    return map { it.toDomain() }
}

fun fromJsonString(jsonString: String): List<ReactionItem?> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString<List<ReactionItemApi?>>(jsonString).map { it?.toDomain() }
}