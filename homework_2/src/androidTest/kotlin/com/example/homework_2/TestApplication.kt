package com.example.homework_2

import android.app.Application
import android.util.Log
import com.example.homework_2.di.DaggerTestAppComponent
import com.example.homework_2.di.TestAppDependencies
import com.example.homework_2.di.app.AppComponentHolder

class TestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val appDependencies = TestAppDependencies { this@TestApplication }
        val testComponent = DaggerTestAppComponent.factory().create(appDependencies)
        AppComponentHolder.appComponent = testComponent
    }
}