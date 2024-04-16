package com.example.homework_2.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitModule {

    private const val BASE_URL = "https://tinkoff-android-spring-2024.zulipchat.com/"
    private const val JSON_MIME_TYPE = "application/json"

    private val json by lazy {
        Json {ignoreUnknownKeys = true}
    }

    private fun init(): Retrofit{
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MIME_TYPE.toMediaType()))
            .client(client)
            .build()
    }

    val create: NetworkInterface = init().create(NetworkInterface::class.java)

}