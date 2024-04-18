package com.example.homework_2.network

import com.example.homework_2.chat.MessageItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Events(
    @SerialName("events")
    val events: List<EventItem>
)

@Serializable
data class EventItem(
    @SerialName("type")
    val type: String,
    @SerialName("message")
    val message: MessageItem? = null
)