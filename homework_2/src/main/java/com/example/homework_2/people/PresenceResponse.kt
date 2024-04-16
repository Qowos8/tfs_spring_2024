package com.example.homework_2.people

import kotlinx.serialization.Serializable

@Serializable
data class PresenceResponse(
    val msg: String,
    val presences: List<PresenceItem>
)