package com.example.homework_2.data.network.mapper

import com.example.homework_2.data.network.model.chat.message.MessageItemApi
import com.example.homework_2.domain.entity.MessageItem

fun MessageItemApi.toDomain(): MessageItem {
    return MessageItem(
        id = id.toInt(),
        userId = userId?.toInt(),
        userFullName = userFullName,
        topicName = topicName,
        avatarUrl = avatarUrl,
        content = content,
        reactions = reactions,
        timestamp = timestamp
    )
}