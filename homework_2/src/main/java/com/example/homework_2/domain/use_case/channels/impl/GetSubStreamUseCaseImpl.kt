package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetSubStreamUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSubStreamUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): GetSubStreamUseCase {
    override fun invoke(): Flow<List<StreamItem>> {
        return repository.getSub()
    }
}