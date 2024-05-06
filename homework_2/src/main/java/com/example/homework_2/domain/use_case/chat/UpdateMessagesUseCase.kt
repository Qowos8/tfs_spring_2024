package com.example.homework_2.domain.use_case.chat

import com.example.homework_2.domain.entity.MessageItem

interface UpdateMessagesUseCase {
    suspend operator fun invoke(narrow: String, streamId: Int, topicName: String, nextCount: Int): List<MessageItem>
}