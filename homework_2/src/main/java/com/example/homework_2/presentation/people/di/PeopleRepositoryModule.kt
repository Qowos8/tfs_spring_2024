package com.example.homework_2.presentation.people.di

import com.example.homework_2.data.impl.PeopleRepositoryImpl
import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.domain.use_case.people.PeopleUseCase
import com.example.homework_2.domain.use_case.people.impl.PeopleUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface PeopleRepositoryModule {

    @Binds
    fun providePeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    fun providePeopleUseCase(peopleUseCaseImpl: PeopleUseCaseImpl): PeopleUseCase
}