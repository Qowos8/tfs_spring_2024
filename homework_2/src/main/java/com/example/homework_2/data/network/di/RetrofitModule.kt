package com.example.homework_2.data.network.di

import com.example.homework_2.data.network.ApiKeyInterceptor
import com.example.homework_2.data.network.api.profile.ProfileApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitModule {

    private const val BASE_URL = "https://tinkoff-android-spring-2024.zulipchat.com/"
    private const val JSON_MIME_TYPE = "application/json"

    private val json by lazy {
        Json {ignoreUnknownKeys = true}
    }

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.MINUTES)
        .writeTimeout(10, TimeUnit.MINUTES)
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MIME_TYPE.toMediaType()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    fun <T> create(apiService: Class<T>): T {
        return retrofit.create(apiService)
    }
}