package com.example.homework_2.domain.use_case.people

import com.example.homework_2.domain.entity.ProfileItem
import kotlinx.coroutines.flow.Flow

interface GetPeopleUseCase {
    operator fun invoke(): Flow<List<ProfileItem>>
}