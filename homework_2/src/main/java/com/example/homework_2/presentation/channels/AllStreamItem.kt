package com.example.homework_2.presentation.channels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllStreamItem(
    @SerialName("stream_id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: String? = null,
    @SerialName("description")
    val description: String,
)

@Serializable
data class SubStreamItem(
    @SerialName("stream_id")
    val streamId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String
)
