package com.example.homework_2

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.chat.MessageItem

class DiffUtilAdapterItemCallback : DiffUtil.ItemCallback<MessageItem>() {
    override fun areItemsTheSame(oldItem: MessageItem, newItem: MessageItem): Boolean {
        return oldItem::class == newItem::class && oldItem.messageId == newItem.messageId
    }

    override fun areContentsTheSame(oldItem: MessageItem, newItem: MessageItem): Boolean {
        return oldItem == newItem
    }
}