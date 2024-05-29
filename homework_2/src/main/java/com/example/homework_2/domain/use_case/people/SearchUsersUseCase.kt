package com.example.homework_2.domain.use_case.people

import com.example.homework_2.domain.entity.ProfileItem

interface SearchUsersUseCase {
    suspend operator fun invoke(query: String, initial: List<ProfileItem>): List<ProfileItem>
}