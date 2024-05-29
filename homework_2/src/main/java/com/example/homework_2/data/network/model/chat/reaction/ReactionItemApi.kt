package com.example.homework_2.data.network.model.chat.reaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionItemApi(
    @SerialName("emoji_code")
    val emojiCode: String ,
    @SerialName("emoji_name")
    val emojiName: String ,
    @SerialName("user_id")
    val userId: Int? ,
    @SerialName("reaction_type")
    val reactionType: String? ,
    @SerialName("user")
    val user: ReactionUserApi? = null
)

@Serializable
data class ReactionUserApi(
    @SerialName("email")
    val email: String?,
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val name: String? = null,
)