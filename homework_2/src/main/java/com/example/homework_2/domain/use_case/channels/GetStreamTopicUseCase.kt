package com.example.homework_2.domain.use_case.channels

import com.example.homework_2.domain.entity.TopicItem

interface GetStreamTopicUseCase {
    suspend operator fun invoke(streamId: Int): List<TopicItem>
}