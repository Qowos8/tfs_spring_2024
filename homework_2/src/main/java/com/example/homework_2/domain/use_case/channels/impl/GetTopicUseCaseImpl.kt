package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetTopicUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopicUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): GetTopicUseCase {
    override fun invoke(streamId: Int): Flow<List<TopicItem>> {
        return repository.getTopics(streamId)
    }
}