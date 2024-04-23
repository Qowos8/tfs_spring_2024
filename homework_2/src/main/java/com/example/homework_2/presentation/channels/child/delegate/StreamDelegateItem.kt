package com.example.homework_2.presentation.channels.child.delegate

import com.example.homework_2.data.network.model.channels.stream.StreamItemApi
import com.example.homework_2.presentation.chat.delegate.MessageDelegateItem
import com.example.homework_2.presentation.delegate.DelegateItem

class StreamDelegateItem(
    val id: Int,
    private val value: StreamItemApi,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as MessageDelegateItem).value == content()
    }
}