package com.example.homework_2

data class MessageItem(
    val messageId: Int,
    val message: String,
    val senderId: Int,
    val senderName: String? = "user",
    val sendTime: String,
    val reactions: MutableMap<String, Int> = mutableMapOf()
)