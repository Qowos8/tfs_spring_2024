package com.example.homework_2.chat.delegate

import com.example.homework_2.chat.MessageItem
import com.example.homework_2.delegate.DelegateItem

class MessageDelegateItem(
    val id: Int,
    private val value: MessageItem,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as MessageDelegateItem).value == content()
    }
}