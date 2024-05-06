package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.ReactionDBItem
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi

fun ReactionDBItem.toDomain(): ReactionItemApi {
    return ReactionItemApi(
        emojiCode = this.emojiCode,
        emojiName = this.emojiName,
        reactionType = this.reactionType,
        userId = this.userId
    )
}