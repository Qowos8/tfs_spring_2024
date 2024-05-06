package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPeople(): Flow<List<ProfileItem>>

    suspend fun updatePeople()
}