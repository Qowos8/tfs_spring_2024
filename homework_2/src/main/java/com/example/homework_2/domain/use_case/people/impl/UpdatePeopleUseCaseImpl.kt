package com.example.homework_2.domain.use_case.people.impl

import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.domain.use_case.people.UpdatePeopleUseCase
import javax.inject.Inject

class UpdatePeopleUseCaseImpl @Inject constructor(
    private val repository: PeopleRepository
): UpdatePeopleUseCase {

    override suspend fun invoke() {
        repository.updatePeople()
    }
}