package com.example.homework_2.presentation.people.di

import com.example.homework_2.data.db.DataBase
import com.example.homework_2.di.app.AppComponentHolder
import retrofit2.Retrofit

interface PeopleDependencies {
    val retrofit: Retrofit
        get() = AppComponentHolder.appComponent.retrofit()
    val dataBase: DataBase
        get() = AppComponentHolder.appComponent.dataBase()
    object Impl : PeopleDependencies
}