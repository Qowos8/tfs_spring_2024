package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.StreamSearchUseCase
import javax.inject.Inject

class StreamSearchUseCaseImpl @Inject constructor(
    private val channelsRepository: ChannelsRepository
): StreamSearchUseCase {
    override suspend fun invoke(query: String, initial: List<StreamItem>): List<StreamItem> {
        return channelsRepository.searchStream(query, initial)
    }
}