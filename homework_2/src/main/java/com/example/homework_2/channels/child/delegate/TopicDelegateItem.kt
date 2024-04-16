package com.example.homework_2.channels.child.delegate

import com.example.homework_2.channels.AllStreamItem
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.chat.delegate.MessageDelegateItem
import com.example.homework_2.delegate.DelegateItem

class TopicDelegateItem(
    val id: Int,
    private val value: TopicItem,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as MessageDelegateItem).value == content()
    }
}