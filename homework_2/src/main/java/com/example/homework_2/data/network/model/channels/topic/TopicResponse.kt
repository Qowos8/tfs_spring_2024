package com.example.homework_2.presentation.channels.child.model.channels.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicResponse(
    @SerialName("msg")
    val message: String,
    @SerialName("result")
    val result: String,
    @SerialName("topics")
    val topics: List<TopicItemApi>
)