package com.example.homework_2.domain.use_case.channels

import com.example.homework_2.domain.entity.TopicItem
import kotlinx.coroutines.flow.Flow

interface GetTopicUseCase {
    operator fun invoke(streamId: Int): Flow<List<TopicItem>>
}