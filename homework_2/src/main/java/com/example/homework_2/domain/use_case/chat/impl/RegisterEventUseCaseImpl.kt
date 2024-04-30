package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.RegisterEventUseCase
import javax.inject.Inject

class RegisterEventUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): RegisterEventUseCase {
    override suspend fun invoke(fetchEventTypes: String, eventTypes: String): RegisterResponse {
        return repository.registerEvent(fetchEventTypes, eventTypes)
    }
}