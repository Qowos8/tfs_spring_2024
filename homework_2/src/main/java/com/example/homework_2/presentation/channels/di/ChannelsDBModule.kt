package com.example.homework_2.presentation.channels.di

import com.example.homework_2.data.db.DataBase
import com.example.homework_2.data.db.dao.StreamDao
import dagger.Module
import dagger.Provides

@Module
object ChannelsDBModule {
    @Provides
    fun provideDB(dataBase: DataBase): StreamDao{
        return dataBase.streamDao()
    }
}