package com.example.homework_2

data class MessageItem(
    val messageId: Int,
    val message: String,
    val senderId: Int,
    val senderName: String? = "user",
    val reactions: MutableMap<String, Int> = mutableMapOf(),
    val reactionsView: MutableList<EmojiCustomView> = mutableListOf(),
)