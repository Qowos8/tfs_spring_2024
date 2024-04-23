package com.example.homework_2.utils

import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.chat.delegate.MessageDelegateItem
import com.example.homework_2.presentation.delegate.DelegateItem

object MessageMapper{
    fun convertToDelegate(messageList: List<MessageItem>): List<DelegateItem>{
        val delegateList: MutableList<DelegateItem> = mutableListOf()

        messageList.forEach{ message ->
            delegateList.add(
                MessageDelegateItem(
                    id = message.id.toInt(),
                    value = message
                )
            )
        }
        return  delegateList
    }
}