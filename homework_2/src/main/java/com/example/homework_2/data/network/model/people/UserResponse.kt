package com.example.homework_2.data.network.model.people

import com.example.homework_2.data.network.model.profile.ProfileItemApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("result")
    val result: String,
    @SerialName("msg")
    val msg: String,
    @SerialName("user")
    val user: ProfileItemApi
)