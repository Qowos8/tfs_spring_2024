package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.MessageDbItem
import com.example.homework_2.data.network.model.chat.message.MessageItemApi
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi
import com.example.homework_2.domain.entity.MessageItem
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
        reactions = toJsonString(reactions)
    )
}

fun List<MessageItemApi>.toDb(streamId: Int): List<MessageDbItem>{
    return map {it.toDB(streamId)}
}

fun toJsonString(reactions: List<ReactionItemApi?>?): String{
    return Json.encodeToString(reactions)
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
        reactions = toJsonString(reactions)
    )
}