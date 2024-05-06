package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.UpdateMessagesUseCase
import javax.inject.Inject

class UpdateMessagesUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): UpdateMessagesUseCase {
    override suspend fun invoke(narrow: String, streamId: Int, topicName: String, nextCount: Int): List<MessageItem> {
        return repository.updateMessages(narrow, streamId, topicName, nextCount)
    }
}