package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.UpdateAllStreamsUseCase
import javax.inject.Inject

class UpdateAllStreamsUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): UpdateAllStreamsUseCase {
    override suspend fun invoke() {
        repository.updateAll()
    }
}