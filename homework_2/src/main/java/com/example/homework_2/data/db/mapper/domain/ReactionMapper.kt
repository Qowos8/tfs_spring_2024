package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.ReactionDBItem
import com.example.homework_2.domain.entity.ReactionItem

fun ReactionDBItem.toDomain(): ReactionItem {
    return ReactionItem(
        emojiCode = emojiCode,
        emojiName = emojiName,
        reactionType = reactionType,
        userId = userId
    )
}