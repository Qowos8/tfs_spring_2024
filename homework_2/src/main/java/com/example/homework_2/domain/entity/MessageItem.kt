package com.example.homework_2.domain.entity

import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi

data class MessageItem(
    val id: Int,
    val userId: Int? = null,
    val userFullName: String? = null,
    val topicName: String,
    val avatarUrl: String? = null,
    val content: String? = null,
    val reactions: MutableList<ReactionItemApi?>? = mutableListOf(),
    val timestamp: Long? = null,
)