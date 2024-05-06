package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.ReactionDBItem
import com.example.homework_2.data.network.model.chat.reaction.ReactionUserApi
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi

fun ReactionItemApi.toBD(messageId: Int): ReactionDBItem {
    return ReactionDBItem(
        id = 0,
        messageId = messageId,
        emojiCode = this.emojiCode!!,
        emojiName = this.emojiName!!,
        reactionType = this.reactionType!!,
        userId = this.userId!!
    )
}
