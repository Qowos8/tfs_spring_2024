package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.DeleteReactionUseCase
import javax.inject.Inject

class DeleteReactionUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): DeleteReactionUseCase {

    override suspend fun invoke(emojiName: String, messageId: Int) {
        return repository.deleteReaction(emojiName, messageId)
    }
}