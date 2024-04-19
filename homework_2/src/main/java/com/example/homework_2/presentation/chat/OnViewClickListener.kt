package com.example.homework_2.presentation.chat

fun interface OnViewClickListener {
    fun onClick(messageId: Int, emojiName: String, count: Int)
}