package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.CreateChannelUseCase
import javax.inject.Inject

class CreateChannelUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): CreateChannelUseCase {
    override suspend fun invoke(name: String, description: String) {
        repository.createStream(name, description)
    }
}