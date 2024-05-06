package com.example.homework_2.domain.use_case.profile

interface UpdateAnotherProfileUseCase {
    suspend fun invoke(profileId: Int)
}