package com.example.homework_2.presentation.profile.di

import com.example.homework_2.data.db.DataBase
import com.example.homework_2.data.db.dao.ProfileDao
import dagger.Module
import dagger.Provides

@Module
internal object ProfileDBModule {
    @Provides
    fun provideDB(dataBase: DataBase): ProfileDao{
        return dataBase.profileDao()
    }
}