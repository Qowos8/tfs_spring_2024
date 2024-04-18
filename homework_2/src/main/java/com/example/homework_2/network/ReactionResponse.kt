package com.example.homework_2.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionResponse(
    @SerialName("emoji_code")
    val emojiCode: String? ,
    @SerialName("emoji_name")
    val emojiName: String? ,
    @SerialName("user_id")
    val userId: Int? ,
    @SerialName("reaction_type")
    val reactionType: String? ,
    @SerialName("user")
    val user: ReactionUser? = null
)

@Serializable
data class ReactionUser(
    @SerialName("email")
    val email: String?,
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val name: String? = null,
)