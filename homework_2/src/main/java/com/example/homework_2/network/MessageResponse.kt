package com.example.homework_2.network

import com.example.homework_2.chat.MessageItem
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerializedName("messages")
    val messages: List<MessageItem>,
)
