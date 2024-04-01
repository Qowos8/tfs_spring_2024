package com.example.homework_2.chat

import com.example.homework_2.view.EmojiCustomView

data class MessageItem(
    val messageId: Int,
    val message: String,
    val senderId: Int,
    val senderName: String? = "user",
    val reactions: MutableMap<String, Int> = mutableMapOf(),
    val reactionsView: MutableList<EmojiCustomView> = mutableListOf(),
)