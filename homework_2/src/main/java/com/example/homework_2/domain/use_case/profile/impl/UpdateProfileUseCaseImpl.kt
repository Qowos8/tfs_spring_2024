package com.example.homework_2.domain.use_case.profile.impl

import com.example.homework_2.domain.repository.ProfileRepository
import com.example.homework_2.domain.use_case.profile.UpdateProfileUseCase
import javax.inject.Inject

class UpdateProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
):UpdateProfileUseCase {
    override suspend fun invoke() {
        repository.updateProfile()
    }
}