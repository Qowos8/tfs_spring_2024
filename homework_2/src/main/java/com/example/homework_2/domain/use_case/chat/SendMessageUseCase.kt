package com.example.homework_2.domain.use_case.chat

fun interface SendMessageUseCase {
    suspend operator fun invoke(streamName: String, topicName: String, content: String)
}