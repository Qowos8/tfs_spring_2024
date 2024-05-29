package com.example.homework_2.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ReactionItem(
    val emojiCode: String,
    val emojiName: String,
    val userId: Int?,
    val reactionType: String?,
    val user: ReactionUser? = null
)

@Serializable
data class ReactionUser(
    val email: String?,
    val id: Long,
    val name: String? = null,
)