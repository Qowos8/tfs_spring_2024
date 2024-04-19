package com.example.homework_2.presentation.channels.child.delegate

import com.example.homework_2.presentation.channels.TopicItem
import com.example.homework_2.presentation.chat.delegate.MessageDelegateItem
import com.example.homework_2.presentation.delegate.DelegateItem

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