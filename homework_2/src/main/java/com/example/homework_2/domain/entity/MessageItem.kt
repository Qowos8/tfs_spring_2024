package com.example.homework_2.domain.entity

import com.example.homework_2.data.network.model.chat.reaction.ReactionResponse

data class MessageItem(
    val id: Long,
    val userId: Long? = null,
    val userFullName: String? = null,
    val topicName: String? = null,
    val avatarUrl: String? = null,
    val content: String? = null,
    val reactions: MutableList<ReactionResponse?>? = mutableListOf(),
    val timestamp: Long? = null,
)