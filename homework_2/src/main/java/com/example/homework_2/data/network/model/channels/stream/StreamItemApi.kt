package com.example.homework_2.data.network.model.channels.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamItemApi(
    @SerialName("stream_id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: String? = null,
    @SerialName("description")
    val description: String,
)
