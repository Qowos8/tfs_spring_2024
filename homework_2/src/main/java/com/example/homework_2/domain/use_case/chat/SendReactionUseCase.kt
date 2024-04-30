package com.example.homework_2.domain.use_case.chat

interface SendReactionUseCase {
    suspend operator fun invoke(emojiName: String, messageId: Int)
}