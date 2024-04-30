package com.example.homework_2.domain.use_case.chat

interface DeleteReactionUseCase {
    suspend operator fun invoke(emojiName: String, messageId: Int)
}