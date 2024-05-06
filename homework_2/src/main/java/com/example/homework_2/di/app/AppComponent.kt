package com.example.homework_2.di.app

import android.content.Context
import com.example.homework_2.data.db.DataBase
import com.example.homework_2.presentation.MainActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(
    dependencies = [
        AppDependencies::class,
    ],
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataBaseModule::class
    ]
)
@Singleton
interface AppComponent {

    fun cicerone(): Cicerone<Router>
    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
    fun context(): Context
    fun retrofit(): Retrofit
    fun dataBase(): DataBase

    fun injectMainActivity(main: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(appDependencies: AppDependencies): AppComponent
    }
}

object AppComponentHolder {
    lateinit var appComponent: AppComponent
}