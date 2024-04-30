package com.example.homework_2.presentation.channels.di

import com.example.homework_2.data.network.api.channels.ChannelsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
object ChannelsNetworkModule {

    @Provides
    fun provideApi(retrofit: Retrofit): ChannelsApi {
        return retrofit.create<ChannelsApi>()
    }
}