package com.example.homework_2.domain.use_case.profile.impl

import com.example.homework_2.domain.repository.AnotherProfileRepository
import com.example.homework_2.domain.use_case.profile.UpdateAnotherProfileUseCase
import javax.inject.Inject

class UpdateAnotherProfileUseCaseImpl @Inject constructor(
    private val repository: AnotherProfileRepository
): UpdateAnotherProfileUseCase {
    override suspend fun invoke(profileId: Int) {
        return repository.updateProfile(profileId)
    }
}