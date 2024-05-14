package com.example.homework_2.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object TestNetworkModule {

    private const val WIREMOCK_BASE_URL = "http://localhost:8080"

    @Provides
    @Singleton
    fun provideWireMockRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WIREMOCK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}