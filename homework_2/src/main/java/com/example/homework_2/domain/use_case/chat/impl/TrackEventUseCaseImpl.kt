package com.example.homework_2.domain.use_case.chat.impl

import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.TrackEventUseCase
import javax.inject.Inject

class TrackEventUseCaseImpl @Inject constructor(
    private val repository: ChatRepository
): TrackEventUseCase {
    override suspend fun invoke(currentId: String, timeOut: Int): Events {
        return repository.trackEvent(currentId, timeOut)
    }
}