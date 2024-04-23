package com.example.homework_2.data.network.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NarrowItem(
    @SerialName("operand")
    val operand: String,
    @SerialName("operator")
    val operator: String
)