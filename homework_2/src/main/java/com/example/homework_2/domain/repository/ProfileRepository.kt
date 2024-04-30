package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile(): ProfileItem
}