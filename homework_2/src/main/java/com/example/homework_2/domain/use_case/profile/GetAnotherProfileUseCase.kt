package com.example.homework_2.domain.use_case.profile

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface GetAnotherProfileUseCase {
    fun invoke(userId: Int): Flow<ProfileItem>
}