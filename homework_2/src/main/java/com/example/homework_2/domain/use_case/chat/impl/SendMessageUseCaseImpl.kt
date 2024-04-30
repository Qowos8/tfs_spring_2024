package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.SendMessageUseCase
import javax.inject.Inject

class SendMessageUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): SendMessageUseCase {
    override suspend fun invoke(streamName: String, topicName: String, content: String) {
        repository.sendMessage(streamName, topicName, content)
    }
}