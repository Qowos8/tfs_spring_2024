package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.ProfileItem

interface AnotherProfileRepository {
    suspend fun getProfile(userId: Int): ProfileItem
}