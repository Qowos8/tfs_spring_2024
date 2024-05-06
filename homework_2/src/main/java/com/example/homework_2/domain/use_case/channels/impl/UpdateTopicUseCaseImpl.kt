package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.UpdateTopicUseCase
import javax.inject.Inject

class UpdateTopicUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): UpdateTopicUseCase {
    override suspend fun invoke(streamId: Int) {
        repository.updateTopic(streamId)
    }
}