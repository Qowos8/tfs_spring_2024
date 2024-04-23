package com.example.homework_2.presentation.channels.child.model.channels.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicItemApi (
    @SerialName("name")
    val name : String,
    @SerialName("max_id")
    val messageCount: Int,
)