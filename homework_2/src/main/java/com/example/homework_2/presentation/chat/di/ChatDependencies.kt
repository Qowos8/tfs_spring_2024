package com.example.homework_2.presentation.chat.di

import com.example.homework_2.di.app.AppComponentHolder
import retrofit2.Retrofit

interface ChatDependencies {
    val retrofit: Retrofit
        get() = AppComponentHolder.appComponent.retrofit()

    object Impl : ChatDependencies
}