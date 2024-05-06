package com.example.homework_2.presentation.chat.di

import com.example.homework_2.data.db.DataBase
import com.example.homework_2.di.app.AppComponentHolder
import retrofit2.Retrofit

interface ChatDependencies {
    val retrofit: Retrofit
        get() = AppComponentHolder.appComponent.retrofit()
    val dataBase: DataBase
        get() = AppComponentHolder.appComponent.dataBase()

    object Impl : ChatDependencies
}