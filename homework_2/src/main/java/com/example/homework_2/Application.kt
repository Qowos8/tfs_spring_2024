package com.example.homework_2
import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class Application: Application() {
    val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: Application
            private set
    }
}