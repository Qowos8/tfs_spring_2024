package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.MessageDbItem
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi
import com.example.homework_2.domain.entity.MessageItem
import kotlinx.serialization.json.Json

fun MessageDbItem.toDomain(): MessageItem {
    return MessageItem(
        id = id,
        userId = userId,
        userFullName = userFullName,
        topicName = topicName,
        avatarUrl = avatarUrl,
        content = content,
        reactions = fromJsonString(reactions)?.toMutableList(),
        timestamp = timestamp
    )
}
fun fromJsonString(jsonString: String): List<ReactionItemApi?>? {
    return Json.decodeFromString<List<ReactionItemApi?>>(jsonString)
}
fun List<MessageDbItem>.toDomain(): List<MessageItem>{
    return map { it.toDomain() }
}