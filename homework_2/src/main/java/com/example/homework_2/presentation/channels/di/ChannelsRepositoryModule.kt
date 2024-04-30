package com.example.homework_2.presentation.channels.di

import com.example.homework_2.data.impl.ChannelsRepositoryImpl
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.GetStreamTopicUseCase
import com.example.homework_2.domain.use_case.channels.GetSubStreamsUseCase
import com.example.homework_2.domain.use_case.channels.StreamSearchUseCase
import com.example.homework_2.domain.use_case.channels.impl.ChannelsGetSubUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.GetAllUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.GetStreamTopicUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.StreamSearchUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ChannelsRepositoryModule {

    @Binds
    fun provideRepository(channelsRepositoryImpl: ChannelsRepositoryImpl): ChannelsRepository

    @Binds
    fun provideGetAllStreamsUseCase(getAllUseCaseImpl: GetAllUseCaseImpl): GetAllStreamsUseCase

    @Binds
    fun provideGetSubStreamsUseCase(getSubUseCaseImpl: ChannelsGetSubUseCaseImpl): GetSubStreamsUseCase

    @Binds
    fun provideStreamTopicUseCase(getStreamTopicUseCaseImpl: GetStreamTopicUseCaseImpl): GetStreamTopicUseCase

    @Binds
    fun provideStreamSearchUseCase(streamSearchUseCaseImpl: StreamSearchUseCaseImpl): StreamSearchUseCase
}