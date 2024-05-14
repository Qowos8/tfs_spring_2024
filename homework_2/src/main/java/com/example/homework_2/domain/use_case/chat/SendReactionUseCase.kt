package com.example.homework_2.domain.use_case.chat

fun interface SendReactionUseCase {
    suspend operator fun invoke(emojiName: String, messageId: Int)
}