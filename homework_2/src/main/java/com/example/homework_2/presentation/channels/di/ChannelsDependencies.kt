package com.example.homework_2.presentation.channels.di

import com.example.homework_2.di.app.AppComponentHolder
import retrofit2.Retrofit

interface ChannelsDependencies {
    val retrofit: Retrofit
        get() = AppComponentHolder.appComponent.retrofit()

    object Impl : ChannelsDependencies
}