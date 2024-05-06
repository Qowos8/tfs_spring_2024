package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.GetMessagesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): GetMessagesUseCase {

    override fun invoke(streamId: Int, topicName: String): Flow<List<MessageItem>> {
        return repository.getMessages(streamId, topicName)
    }
}