package com.example.homework_2.domain.use_case.profile

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    suspend fun invoke(): ProfileItem
}