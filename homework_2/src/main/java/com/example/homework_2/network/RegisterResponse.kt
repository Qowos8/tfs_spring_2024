package com.example.homework_2.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("queue_id")
    val id: String,
    @SerialName("last_event_id")
    val lastEventId: Int
)

