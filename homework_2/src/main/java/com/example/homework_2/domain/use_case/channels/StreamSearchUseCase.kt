package com.example.homework_2.domain.use_case.channels

import com.example.homework_2.domain.entity.StreamItem

interface StreamSearchUseCase {
    suspend operator fun invoke(query: String, initial: List<StreamItem>): List<StreamItem>
}