package com.example.homework_2.presentation.chat.di

import com.example.homework_2.data.network.api.chat.ChatApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
object ChatNetworkModule {

    @Provides
    fun provideApi(retrofit: Retrofit): ChatApi{
        return retrofit.create<ChatApi>()
    }
}