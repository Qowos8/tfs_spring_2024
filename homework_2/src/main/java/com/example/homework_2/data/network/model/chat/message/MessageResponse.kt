package com.example.homework_2.presentation.channels.child.model.chat.message

import com.example.homework_2.data.network.model.chat.message.MessageItemApi
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerializedName("messages")
    val messages: List<MessageItemApi>,
)
