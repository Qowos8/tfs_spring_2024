package com.example.homework_2.bottom_sheet

import com.example.homework_2.chat.MessageItem

interface BottomSheetClickListener {
    fun onEmojiClicked(messageItem: MessageItem, emoji: String)
}
