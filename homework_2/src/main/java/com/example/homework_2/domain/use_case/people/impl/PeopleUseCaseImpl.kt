package com.example.homework_2.domain.use_case.people.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.domain.use_case.people.PeopleUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleUseCaseImpl @Inject constructor(private val repository: PeopleRepository): PeopleUseCase {
    override suspend fun invoke(): List<ProfileItem> {
        return repository.getPeople()
    }
}