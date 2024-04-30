package com.example.homework_2.presentation.profile.di

import com.example.homework_2.data.impl.AnotherProfileRepositoryImpl
import com.example.homework_2.data.impl.ProfileRepositoryImpl
import com.example.homework_2.domain.repository.AnotherProfileRepository
import com.example.homework_2.domain.repository.ProfileRepository
import com.example.homework_2.domain.use_case.profile.AnotherProfileUseCase
import com.example.homework_2.domain.use_case.profile.ProfileUseCase
import com.example.homework_2.domain.use_case.profile.impl.AnotherProfileUseCaseImpl
import com.example.homework_2.domain.use_case.profile.impl.ProfileUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ProfileRepositoryModule {

    @Binds
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun provideProfileUseCase(profileUseCaseImpl: ProfileUseCaseImpl): ProfileUseCase

    @Binds
    fun provideAnotherProfileUseCase(profileUseCaseImpl: AnotherProfileUseCaseImpl): AnotherProfileUseCase

    @Binds
    fun provideAnotherProfileRepository(profileRepositoryImpl: AnotherProfileRepositoryImpl): AnotherProfileRepository
}
