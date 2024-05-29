package com.example.homework_2.di

import com.example.homework_2.data.network.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object TestNetworkModule {

    private const val WIREMOCK_BASE_URL = "http://localhost:8080"

    @Provides
    @Singleton
    fun provideWireMockRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(WIREMOCK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}