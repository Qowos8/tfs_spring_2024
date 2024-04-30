package com.example.homework_2.data.impl

import com.example.homework_2.data.network.api.profile.ProfileApi
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.AnotherProfileRepository
import javax.inject.Inject

class AnotherProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi
): AnotherProfileRepository {
    override suspend fun getProfile(userId: Int): ProfileItem {
        return api.getUser(userId).user.toDomain()
    }
}