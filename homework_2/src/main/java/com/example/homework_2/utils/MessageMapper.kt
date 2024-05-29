package com.example.homework_2.utils

import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.chat.delegate.MessageDelegateItem
import com.example.homework_2.presentation.delegate.DelegateItem

object MessageMapper {
    fun convertListToDelegate(messageList: List<MessageItem>): List<DelegateItem> {
        val delegateList: MutableList<DelegateItem> = mutableListOf()

        messageList.forEach { message ->
            delegateList.add(
                MessageDelegateItem(
                    id = message.id,
                    value = message
                )
            )
        }
        return delegateList
    }

    fun convertSingleToDelegate(message: MessageItem): DelegateItem{
        return MessageDelegateItem(
            id = message.id,
            value = message
        )
    }

    fun convertFromDelegate(item: DelegateItem): MessageItem {
        return MessageItem(
            id  = item.id(),
            userId = (item.content() as MessageItem).userId,
            userFullName = (item.content() as MessageItem).userFullName,
            topicName = (item.content() as MessageItem).topicName,
            avatarUrl = (item.content() as MessageItem).avatarUrl,
            content = (item.content() as MessageItem).content,
            reactions = (item.content() as MessageItem).reactions,
            timestamp = (item.content() as MessageItem).timestamp
        )
    }
}