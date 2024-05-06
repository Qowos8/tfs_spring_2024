package com.example.homework_2.domain.use_case.channels

interface UpdateTopicUseCase {
    suspend operator fun invoke(streamId: Int)
}