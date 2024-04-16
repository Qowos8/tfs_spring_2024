package com.example.homework_2.channels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicItem (
    @SerialName("name")
    val name : String,
    @SerialName("max_id")
    val messageCount: Int,
)