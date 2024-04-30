package com.example.homework_2.domain.use_case.channels

import com.example.homework_2.domain.entity.StreamItem

interface GetSubStreamsUseCase {
    suspend operator fun invoke(): List<StreamItem>
}