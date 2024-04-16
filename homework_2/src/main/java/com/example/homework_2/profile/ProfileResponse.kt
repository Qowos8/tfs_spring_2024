package com.example.homework_2.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("members")
    val members: List<ProfileItem>
)