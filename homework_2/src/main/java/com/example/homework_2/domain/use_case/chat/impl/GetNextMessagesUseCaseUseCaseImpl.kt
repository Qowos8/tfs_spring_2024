package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.GetNextMessagesUseCase
import javax.inject.Inject

class GetNextMessagesUseCaseUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): GetNextMessagesUseCase {

    override suspend fun invoke(streamId: Int, topicName: String, newMessageItem: MessageItem){
        repository.getNextMessage(streamId, topicName, newMessageItem)
    }
}