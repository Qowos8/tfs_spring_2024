package com.example.homework_2
import android.app.Application
import com.example.homework_2.di.app.AppComponentHolder
import com.example.homework_2.di.app.AppDependencies
import com.example.homework_2.di.app.DaggerAppComponent

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

       AppComponentHolder.appComponent = DaggerAppComponent.factory().create { this }
    }
}