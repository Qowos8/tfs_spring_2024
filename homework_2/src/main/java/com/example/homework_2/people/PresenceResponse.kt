package com.example.homework_2.people

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceResponse(
    @SerialName("msg")
    val msg: String,
    @SerialName("presences")
    val presences: List<PresenceItem>,
    @SerialName("server_timestamp")
    val serverTimestamp: Float
)