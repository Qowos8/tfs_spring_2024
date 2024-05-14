package com.example.homework_2.di

import com.example.homework_2.di.app.AppDependencies
import retrofit2.Retrofit

interface TestChatDependencies : AppDependencies {
    fun wireMockRetrofit(): Retrofit
}