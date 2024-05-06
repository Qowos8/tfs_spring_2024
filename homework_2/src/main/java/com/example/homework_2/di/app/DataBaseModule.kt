package com.example.homework_2.di.app

import android.content.Context
import androidx.room.Room
import com.example.homework_2.data.db.DataBase
import com.example.homework_2.data.db.dao.ProfileDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {
    private const val BASE_NAME = "database"

    @Provides
    @Singleton
    fun provideDataBase(context: Context): DataBase{
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            BASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}