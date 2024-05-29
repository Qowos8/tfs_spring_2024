package com.example.homework_2.domain.entity

data class MessageItem(
    val id: Int,
    val userId: Int? = null,
    val userFullName: String? = null,
    val topicName: String,
    val avatarUrl: String? = null,
    val content: String? = null,
    val reactions: MutableList<ReactionItem?>? = mutableListOf(),
    val timestamp: Long,
)