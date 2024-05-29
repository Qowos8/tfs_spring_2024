package com.example.homework_2.utils

import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.child.delegate.TopicDelegateItem
import com.example.homework_2.presentation.delegate.DelegateItem

object TopicDelegateMapper {
    fun convertToDelegate(topics: List<TopicItem>): List<DelegateItem> {
        val delegateList: MutableList<DelegateItem> = mutableListOf()
        topics.forEach { topic ->
            delegateList.add(
                TopicDelegateItem(
                    id = topic.messageCount,
                    value = topic
                )
            )
        }
        return delegateList
    }
}

fun generateUniqueId(streamId: Int, maxMessageId: Int): Int {
    return (streamId shl 16) or (maxMessageId and 0xFFFF)
}