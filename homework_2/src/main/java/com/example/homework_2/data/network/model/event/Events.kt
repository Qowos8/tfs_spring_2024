package com.example.homework_2.data.network.model.event

import com.example.homework_2.data.network.model.chat.message.MessageItemApi
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
    val message: MessageItemApi?
)