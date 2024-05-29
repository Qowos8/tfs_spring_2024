package com.example.homework_2.presentation.channels.child.delegate

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.presentation.delegate.DelegateItem

class StreamDelegateItem(
    val id: Int,
    private val value: StreamItem,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as StreamDelegateItem).value == content()
    }
}