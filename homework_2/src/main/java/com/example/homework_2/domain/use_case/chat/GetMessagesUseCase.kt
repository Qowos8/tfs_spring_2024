package com.example.homework_2.domain.use_case.chat

import com.example.homework_2.domain.entity.MessageItem

interface GetMessagesUseCase {
    suspend operator fun invoke(narrow: String): List<MessageItem>
}