package com.example.homework_2.domain.use_case.people.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.domain.use_case.people.GetPeopleUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCaseImpl @Inject constructor(
    private val repository: PeopleRepository
): GetPeopleUseCase {
    override fun invoke(): Flow<List<ProfileItem>> {
        return repository.getPeople()
    }
}