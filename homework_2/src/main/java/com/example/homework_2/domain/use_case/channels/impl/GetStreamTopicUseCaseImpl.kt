package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetStreamTopicUseCase
import javax.inject.Inject

class GetStreamTopicUseCaseImpl @Inject constructor(
    private val channelsRepository: ChannelsRepository
): GetStreamTopicUseCase {
    override suspend fun invoke(streamId: Int): List<TopicItem> {
        return channelsRepository.getTopics(streamId)
    }
}