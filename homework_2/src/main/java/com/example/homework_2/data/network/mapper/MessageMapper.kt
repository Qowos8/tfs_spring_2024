package com.example.homework_2.data.network.mapper

import com.example.homework_2.data.db.mapper.domain.toDomain
import com.example.homework_2.data.network.model.chat.message.MessageItemApi
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi
import com.example.homework_2.data.network.model.chat.reaction.ReactionUserApi
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.entity.ReactionItem
import com.example.homework_2.domain.entity.ReactionUser

fun MessageItemApi.toDomain(): MessageItem {
    return MessageItem(
        id = id.toInt(),
        userId = userId?.toInt(),
        userFullName = userFullName,
        topicName = topicName,
        avatarUrl = avatarUrl,
        content = content,
        reactions = reactions?.map { it?.toDomain() }?.toMutableList(),
        timestamp = timestamp
    )
}

fun ReactionUserApi.toDomain(): ReactionUser {
    return ReactionUser(
        email = email,
        id = id,
        name = name
    )
}

fun List<ReactionItemApi>.toDomain(): List<ReactionItem>{
    return map { it.toDomain() }
}

fun ReactionItemApi.toDomain(): ReactionItem {
    return ReactionItem(
        emojiCode = emojiCode,
        emojiName = emojiName,
        userId = userId,
        reactionType = reactionType,
        user = user?.toDomain()
    )
}