package com.example.homework_2.domain.use_case.profile.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.AnotherProfileRepository
import com.example.homework_2.domain.use_case.profile.AnotherProfileUseCase
import javax.inject.Inject

class AnotherProfileUseCaseImpl @Inject constructor(
    private val repository: AnotherProfileRepository
): AnotherProfileUseCase {
    override suspend fun invoke(userId: Int): ProfileItem {
        return repository.getProfile(userId)
    }
}