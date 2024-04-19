package com.example.homework_2.data.network.model

import com.example.homework_2.presentation.chat.MessageItem
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerializedName("messages")
    val messages: List<MessageItem>,
)
