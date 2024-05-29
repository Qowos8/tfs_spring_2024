package com.example.homework_2.di

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface TestAppModule {

    companion object {
        @Provides
        @Singleton
        fun provideContext(application: Application): Context = application

        @Provides
        @Singleton
        fun provideApplication(): Application = ApplicationProvider.getApplicationContext()

        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

        @Provides
        @Singleton
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
            cicerone.getNavigatorHolder()
    }
}