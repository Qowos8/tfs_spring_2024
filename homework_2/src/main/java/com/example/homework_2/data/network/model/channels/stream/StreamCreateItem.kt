package com.example.homework_2.data.network.model.channels.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamCreateItem(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String
)