package com.example.homework_2.data.network.model.chat.message

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerializedName("messages")
    val messages: List<MessageItemApi>,
)
