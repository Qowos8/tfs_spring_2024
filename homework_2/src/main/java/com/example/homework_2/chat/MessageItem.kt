package com.example.homework_2.chat

import com.example.homework_2.network.ReactionResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageItem(
    @SerialName("id")
    val id: Long,
    @SerialName("sender_id")
    val userId: Long? = null,
    @SerialName("sender_full_name")
    val userFullName: String? = null,
    @SerialName("subject")
    val topicName: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("reactions")
    val reactions: MutableList<ReactionResponse?>? = mutableListOf(),
    @SerialName("timestamp")
    val timestamp: Long? = null,
)