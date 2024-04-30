package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetSubStreamsUseCase
import javax.inject.Inject

class ChannelsGetSubUseCaseImpl @Inject constructor(
    private val channelsRepository: ChannelsRepository
): GetSubStreamsUseCase {
    override suspend fun invoke(): List<StreamItem> {
        return channelsRepository.getSub()
    }
}