package com.example.homework_2.domain.use_case.profile.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.ProfileRepository
import com.example.homework_2.domain.use_case.profile.GetProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : GetProfileUseCase {

    override fun invoke(): Flow<ProfileItem> {
        return profileRepository.getProfile()
    }
}