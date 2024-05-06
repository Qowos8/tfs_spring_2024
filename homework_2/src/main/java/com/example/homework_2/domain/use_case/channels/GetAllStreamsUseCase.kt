package com.example.homework_2.domain.use_case.channels

import com.example.homework_2.domain.entity.StreamItem
import kotlinx.coroutines.flow.Flow

interface GetAllStreamsUseCase {
    operator fun invoke(): Flow<List<StreamItem>>
}