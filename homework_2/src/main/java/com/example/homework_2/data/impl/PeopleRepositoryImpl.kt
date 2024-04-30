package com.example.homework_2.data.impl

import com.example.homework_2.data.network.api.profile.ProfileApi
import com.example.homework_2.di.app.NetworkModule
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
) : PeopleRepository {
    override suspend fun getPeople(): List<ProfileItem> {
        return api.getUsers().members.map { it.toDomain() }
    }
}