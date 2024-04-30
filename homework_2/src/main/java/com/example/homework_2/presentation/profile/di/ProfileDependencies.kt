package com.example.homework_2.presentation.profile.di

import com.example.homework_2.di.app.AppComponentHolder
import retrofit2.Retrofit

interface ProfileDependencies {
    val retrofit: Retrofit
        get() = AppComponentHolder.appComponent.retrofit()

    object Impl : ProfileDependencies
}