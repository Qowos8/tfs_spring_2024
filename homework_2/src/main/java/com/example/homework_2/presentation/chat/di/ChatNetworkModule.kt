package com.example.homework_2.presentation.chat.di

import com.example.homework_2.data.network.api.ChatApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
internal object ChatNetworkModule {

    @Provides
    fun provideApi(retrofit: Retrofit): ChatApi {
        return retrofit.create<ChatApi>()
    }
}