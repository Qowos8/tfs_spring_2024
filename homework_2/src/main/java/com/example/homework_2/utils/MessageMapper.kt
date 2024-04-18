package com.example.homework_2.utils

import com.example.homework_2.chat.MessageItem
import com.example.homework_2.chat.delegate.MessageDelegateItem
import com.example.homework_2.delegate.DelegateItem

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