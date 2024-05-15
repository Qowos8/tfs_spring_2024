package com.example.homework_2.di

import com.example.homework_2.di.app.AppComponent
import com.example.homework_2.di.app.AppDependencies
import com.example.homework_2.di.app.AppModule
import com.example.homework_2.di.app.DataBaseModule
import com.example.homework_2.presentation.chat.di.ChatDependencies
import com.example.homework_2.presentation.chat.di.ChatRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        AppDependencies::class,
                   ],
    modules = [
        AppModule::class,
        TestNetworkModule::class,
        TestNetworkModuleImpl::class,
        DataBaseModule::class,
        ChatRepositoryModule::class
    ]
)
@Singleton
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appDependencies: AppDependencies): TestAppComponent
    }
}