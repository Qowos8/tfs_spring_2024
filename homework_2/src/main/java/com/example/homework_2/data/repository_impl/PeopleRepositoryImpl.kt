package com.example.homework_2.data.repository_impl

import com.example.homework_2.data.db.dao.ProfileDao
import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.data.db.mapper.domain.toDomain
import com.example.homework_2.data.network.api.profile.ProfileApi
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val dao: ProfileDao
) : PeopleRepository {

    override fun getPeople(): Flow<List<ProfileItem>> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun updatePeople() {
        val peopleApi = api.getUsers()
        dao.insertUsers(peopleApi.members.toDB())
    }
}
