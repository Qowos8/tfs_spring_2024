package com.example.homework_2.domain.use_case.chat

import com.example.homework_2.domain.entity.MessageItem
import kotlinx.coroutines.flow.Flow

fun interface GetMessagesUseCase {
    operator fun invoke(streamId: Int, topicName: String): Flow<List<MessageItem>>
}