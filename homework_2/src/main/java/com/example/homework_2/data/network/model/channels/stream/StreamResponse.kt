package com.example.homework_2.data.network.model.channels.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamResponseAll(
    @SerialName("msg")
    val message: String,
    @SerialName("result")
    val result: String,
    @SerialName("streams")
    val subscriptions: List<StreamItemApi>
)

@Serializable
data class StreamResponseSub(
    @SerialName("msg")
    val message: String,
    @SerialName("result")
    val result: String,
    @SerialName("subscriptions")
    val subscriptions: List<StreamItemApi>
)