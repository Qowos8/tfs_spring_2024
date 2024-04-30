package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import javax.inject.Inject

class GetAllUseCaseImpl @Inject constructor(
    private val channelsRepository: ChannelsRepository
): GetAllStreamsUseCase {
    override suspend fun invoke(): List<StreamItem> {
        return channelsRepository.getAll()
    }
}