package com.example.homework_2.presentation.people.di

import com.example.homework_2.data.repository_impl.PeopleRepositoryImpl
import com.example.homework_2.domain.repository.PeopleRepository
import com.example.homework_2.domain.use_case.people.GetPeopleUseCase
import com.example.homework_2.domain.use_case.people.UpdatePeopleUseCase
import com.example.homework_2.domain.use_case.people.impl.GetPeopleUseCaseImpl
import com.example.homework_2.domain.use_case.people.impl.UpdatePeopleUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface PeopleRepositoryModule {

    @Binds
    fun providePeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    fun provideGetNetworkPeopleUseCase(peopleUseCaseImpl: GetPeopleUseCaseImpl): GetPeopleUseCase

    @Binds
    fun provideInsertDBPeople(insertPeopleDBUseCaseImpl: UpdatePeopleUseCaseImpl): UpdatePeopleUseCase
}