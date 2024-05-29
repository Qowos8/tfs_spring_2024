package com.example.homework_2.presentation.chat.emoji

fun interface OnEmojiClickListener {
    fun onClick(messageId: Int, emojiName: String, count: EmojiClickState)
}