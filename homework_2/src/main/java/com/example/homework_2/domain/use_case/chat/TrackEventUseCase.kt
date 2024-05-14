package com.example.homework_2.domain.use_case.chat

import com.example.homework_2.data.network.model.event.Events

fun interface TrackEventUseCase {
    suspend operator fun invoke(currentId: String, timeOut: Int): Events
}