package com.example.homework_2.data.repository_impl

import com.example.homework_2.data.db.dao.ProfileDao
import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.data.db.mapper.domain.toDomain
import com.example.homework_2.data.network.api.ProfileApi
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.AnotherProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AnotherProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val dao: ProfileDao
): AnotherProfileRepository {

    override fun getProfile(userId: Int): Flow<ProfileItem> {
        return dao.getUser(userId).map { it.toDomain() }
    }

    override suspend fun updateProfile(userId: Int) {
        val profileApi = api.getUser(userId)
        dao.insertUser(profileApi.user.toDB())
    }
}