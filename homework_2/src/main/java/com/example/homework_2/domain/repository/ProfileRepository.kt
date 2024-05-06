package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(): Flow<ProfileItem>

    suspend fun updateProfile()
}