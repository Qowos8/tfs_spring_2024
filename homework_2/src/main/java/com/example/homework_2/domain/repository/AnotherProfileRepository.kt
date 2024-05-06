package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface AnotherProfileRepository {
    fun getProfile(userId: Int): Flow<ProfileItem>

    suspend fun updateProfile(userId: Int)
}