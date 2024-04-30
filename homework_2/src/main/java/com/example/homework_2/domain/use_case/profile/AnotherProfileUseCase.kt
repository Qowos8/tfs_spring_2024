package com.example.homework_2.domain.use_case.profile

import com.example.homework_2.domain.entity.ProfileItem

interface AnotherProfileUseCase {
    suspend fun invoke(userId: Int): ProfileItem
}