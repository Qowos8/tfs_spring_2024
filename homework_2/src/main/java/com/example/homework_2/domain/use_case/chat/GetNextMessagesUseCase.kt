package com.example.homework_2.domain.use_case.chat

import com.example.homework_2.domain.entity.MessageItem

fun interface GetNextMessagesUseCase {
    suspend operator fun invoke(
        streamId: Int,
        topicName: String,
        newMessageItem: MessageItem,
    )
}