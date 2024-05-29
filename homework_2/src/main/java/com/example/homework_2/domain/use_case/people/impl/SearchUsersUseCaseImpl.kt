package com.example.homework_2.domain.use_case.people.impl

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.domain.use_case.people.SearchUsersUseCase
import javax.inject.Inject

class SearchUsersUseCaseImpl @Inject constructor(
    private val peopleRepository: PeopleRepository
): SearchUsersUseCase {
    override suspend fun invoke(query: String, initial: List<ProfileItem>): List<ProfileItem> {
        return peopleRepository.searchUsers(query, initial)
    }
}