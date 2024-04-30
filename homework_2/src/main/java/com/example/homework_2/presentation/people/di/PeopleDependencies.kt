package com.example.homework_2.presentation.people.di

import com.example.homework_2.di.app.AppComponentHolder
import retrofit2.Retrofit

interface PeopleDependencies {
    val retrofit: Retrofit
        get() = AppComponentHolder.appComponent.retrofit()

    object Impl : PeopleDependencies
}