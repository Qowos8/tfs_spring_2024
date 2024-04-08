package com.example.homework_2.chat

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.homework_2.view.EmojiCustomView

interface EmojiService {
    fun addEmojiView(context: Context, emoji: String, count: Int, binding: ViewGroup, message: MessageItem)
    fun changeReaction(binding: ViewGroup, emojiView: EmojiCustomView, messageItem: MessageItem)
    fun setAddButton(view: ImageView, binding: ViewGroup, message: MessageItem, onItemClick: (MessageItem) -> Unit)
}