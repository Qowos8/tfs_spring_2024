package com.example.homework_2.domain.use_case.channels.impl

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllStreamsUseCaseImpl @Inject constructor(
    private val repository: ChannelsRepository
): GetAllStreamsUseCase {
    override fun invoke(): Flow<List<StreamItem>> {
        return repository.getAll()
    }
}