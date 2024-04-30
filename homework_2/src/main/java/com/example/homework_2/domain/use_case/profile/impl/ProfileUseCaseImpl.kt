package com.example.homework_2.domain.use_case.profile.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.ProfileRepository
import com.example.homework_2.domain.use_case.profile.ProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ProfileUseCase {

    override suspend fun invoke(): ProfileItem {
        return profileRepository.getProfile()
    }
}