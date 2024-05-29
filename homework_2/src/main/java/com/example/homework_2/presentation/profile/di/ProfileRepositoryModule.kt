package com.example.homework_2.presentation.profile.di

import com.example.homework_2.data.repository_impl.AnotherProfileRepositoryImpl
import com.example.homework_2.data.repository_impl.ProfileRepositoryImpl
import com.example.homework_2.domain.repository.AnotherProfileRepository
import com.example.homework_2.domain.repository.ProfileRepository
import com.example.homework_2.domain.use_case.profile.GetAnotherProfileUseCase
import com.example.homework_2.domain.use_case.profile.UpdateAnotherProfileUseCase
import com.example.homework_2.domain.use_case.profile.GetProfileUseCase
import com.example.homework_2.domain.use_case.profile.UpdateProfileUseCase
import com.example.homework_2.domain.use_case.profile.impl.GetAnotherProfileUseCaseImpl
import com.example.homework_2.domain.use_case.profile.impl.UpdateAnotherProfileUseCaseImpl
import com.example.homework_2.domain.use_case.profile.impl.GetProfileUseCaseImpl
import com.example.homework_2.domain.use_case.profile.impl.UpdateProfileUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ProfileRepositoryModule {

    @Binds
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun provideProfileDBUseCase(profileUseCaseImpl: UpdateAnotherProfileUseCaseImpl): UpdateAnotherProfileUseCase

    @Binds
    fun provideAnotherProfileDBUseCase(anotherProfileUseCaseImpl: GetAnotherProfileUseCaseImpl): GetAnotherProfileUseCase

    @Binds
    fun provideProfileNetworkUseCase(profileUseCaseImpl: GetProfileUseCaseImpl): GetProfileUseCase

    @Binds
    fun provideAnotherProfileRepository(profileRepositoryImpl: AnotherProfileRepositoryImpl): AnotherProfileRepository

    @Binds
    fun provideInsertProfileUseCase(insertDBProfileUseCaseImpl: UpdateProfileUseCaseImpl): UpdateProfileUseCase
}
