package com.example.homework_2.domain.use_case.channels

interface CreateChannelUseCase {
    suspend operator fun invoke(name: String, description: String = "")
}