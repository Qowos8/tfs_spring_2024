package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.UpdateSubStreamUseCase
import javax.inject.Inject

class UpdateSubStreamsUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): UpdateSubStreamUseCase {
    override suspend fun invoke() {
        repository.updateSub()
    }
}