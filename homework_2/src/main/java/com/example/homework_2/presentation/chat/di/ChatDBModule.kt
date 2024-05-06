package com.example.homework_2.presentation.chat.di

import com.example.homework_2.data.db.DataBase
import com.example.homework_2.data.db.dao.MessageDao
import dagger.Module
import dagger.Provides

@Module
object ChatDBModule {
    @Provides
    fun provideDB(dataBase: DataBase): MessageDao {
        return dataBase.messageDao()
    }
}