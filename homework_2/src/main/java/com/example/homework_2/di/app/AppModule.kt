package com.example.homework_2.di.app

import android.content.Context
import com.example.homework_2.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindContext(application: Application): Context

    companion object {
        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(cicerone : Cicerone<Router>): Router = cicerone.router

        @Provides
        @Singleton
        fun provideNavigatorHolder(cicerone : Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
    }
}