package com.example.homework_2.data.repository_impl

import com.example.homework_2.data.db.dao.ProfileDao
import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.data.db.mapper.domain.toDomain
import com.example.homework_2.data.network.api.ProfileApi
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val dao: ProfileDao
) : ProfileRepository {

    override fun getProfile(): Flow<ProfileItem> {
        return dao.getUser(MOCK_USER_ME).map { it.toDomain() }
    }

    override suspend fun updateProfile() {
        val profileApi = api.getUser(MOCK_USER_ME)
        dao.insertUser(profileApi.user.toDB())
    }

    private companion object{
        private const val MOCK_USER_ME = 709571
    }
}