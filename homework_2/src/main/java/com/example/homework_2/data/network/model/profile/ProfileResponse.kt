package com.example.homework_2.data.network.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("members")
    val members: List<ProfileItemApi>
)