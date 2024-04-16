package com.example.homework_2.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileItem (
    @SerialName("user_id")
    val id: Int,
    @SerialName("full_name")
    val name: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("avatar_url")
    val url: String?,
    @SerialName("email")
    val email: String?,
)