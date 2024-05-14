package com.example.homework_2.di

import com.example.homework_2.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestDependencyImpl(private val application: Application) : TestChatDependencies {
    override fun application(): Application = application

    override fun wireMockRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}