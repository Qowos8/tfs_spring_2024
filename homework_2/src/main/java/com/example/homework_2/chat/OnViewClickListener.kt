package com.example.homework_2.chat

fun interface OnViewClickListener {
    fun onClick(messageId: Int, emojiName: String, count: Int)
}