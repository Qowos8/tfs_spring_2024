package com.example.homework_2.people

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceItem(
    @SerialName("{user_email}")
    val email: Aggregated
)

@Serializable
data class Aggregated(
    @SerialName("client")
    val client: String,
    @SerialName("status")
    val status: String,
    @SerialName("timestamp")
    val timestamp: Int
)