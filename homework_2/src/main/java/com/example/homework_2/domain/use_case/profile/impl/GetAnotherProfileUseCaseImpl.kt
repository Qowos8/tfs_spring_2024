package com.example.homework_2.domain.use_case.profile.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.AnotherProfileRepository
import com.example.homework_2.domain.use_case.profile.GetAnotherProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnotherProfileUseCaseImpl @Inject constructor(
    private val repository: AnotherProfileRepository
): GetAnotherProfileUseCase {
    override fun invoke(userId: Int): Flow<ProfileItem> {
        return repository.getProfile(userId)
    }
}