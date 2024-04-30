package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.SendReactionUseCase
import javax.inject.Inject

class SendReactionUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): SendReactionUseCase {
    override suspend fun invoke(emojiName: String, messageId: Int) {
        repository.sendReaction(emojiName, messageId)
    }
}