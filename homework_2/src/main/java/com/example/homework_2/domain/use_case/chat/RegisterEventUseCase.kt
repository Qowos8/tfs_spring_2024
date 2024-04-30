package com.example.homework_2.domain.use_case.chat

import com.example.homework_2.data.network.model.event.RegisterResponse

interface RegisterEventUseCase {
    suspend operator fun invoke(fetchEventTypes: String, eventTypes: String): RegisterResponse
}