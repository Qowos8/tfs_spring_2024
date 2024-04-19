package com.example.homework_2.presentation.chat.delegate

import com.example.homework_2.presentation.chat.MessageItem
import com.example.homework_2.presentation.delegate.DelegateItem

class MessageDelegateItem(
    val id: Int,
    val value: MessageItem,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as MessageDelegateItem).value == content()
    }
}