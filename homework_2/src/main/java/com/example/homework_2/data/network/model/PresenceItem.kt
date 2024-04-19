package com.example.homework_2.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceItem(
    @SerialName("{user_email}")
    val email: Aggregated,
    @SerialName("website")
    val website: Website
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

@Serializable
data class Website(
    @SerialName("client")
    val client: String,
    @SerialName("status")
    val status: String,
    @SerialName("timestamp")
    val timestamp: Int
)