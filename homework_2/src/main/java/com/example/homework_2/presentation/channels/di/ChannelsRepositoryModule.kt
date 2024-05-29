package com.example.homework_2.presentation.channels.di

import com.example.homework_2.data.repository_impl.ChannelsRepositoryImpl
import com.example.homework_2.domain.repository.ChannelsRepository
import com.example.homework_2.domain.use_case.channels.CreateChannelUseCase
import com.example.homework_2.domain.use_case.channels.GetAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.GetSubStreamUseCase
import com.example.homework_2.domain.use_case.channels.GetTopicUseCase
import com.example.homework_2.domain.use_case.channels.UpdateAllStreamsUseCase
import com.example.homework_2.domain.use_case.channels.UpdateSubStreamUseCase
import com.example.homework_2.domain.use_case.channels.UpdateTopicUseCase
import com.example.homework_2.domain.use_case.channels.StreamSearchUseCase
import com.example.homework_2.domain.use_case.channels.impl.CreateChannelUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.GetAllStreamsUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.GetSubStreamUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.GetTopicUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.UpdateAllStreamsUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.UpdateSubStreamsUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.UpdateTopicUseCaseImpl
import com.example.homework_2.domain.use_case.channels.impl.StreamSearchUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ChannelsRepositoryModule {

    @Binds
    fun provideRepository(channelsRepositoryImpl: ChannelsRepositoryImpl): ChannelsRepository

    @Binds
    fun provideStreamSearchUseCase(streamSearchUseCaseImpl: StreamSearchUseCaseImpl): StreamSearchUseCase

    @Binds
    fun provideGetDBAllStreamUseCase(getDBAllStreamUseCaseImpl: GetAllStreamsUseCaseImpl): GetAllStreamsUseCase

    @Binds
    fun provideGetDBSubStreamUseCase(getDBSubStreamsUseCaseImpl: GetSubStreamUseCaseImpl): GetSubStreamUseCase

    @Binds
    fun provideGetDBTopicUseCase(getDBTopicUseCaseImpl: GetTopicUseCaseImpl): GetTopicUseCase

    @Binds
    fun provideInsertDBAllStreamUseCase(insertDBAllStreamsUseCaseImpl: UpdateAllStreamsUseCaseImpl): UpdateAllStreamsUseCase

    @Binds
    fun provideInsertDBSubStreamUseCase(insertDBSubStreamsUseCaseImpl: UpdateSubStreamsUseCaseImpl): UpdateSubStreamUseCase

    @Binds
    fun provideInsertDBTopicUseCase(insertDBTopicUseCaseImpl: UpdateTopicUseCaseImpl): UpdateTopicUseCase

    @Binds
    fun provideCreateStreamUseCase(createChannelUseCase: CreateChannelUseCaseImpl): CreateChannelUseCase
}