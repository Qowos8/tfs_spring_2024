package com.example.homework_2.di

import com.example.homework_2.di.app.AppComponent
import com.example.homework_2.di.app.DataBaseModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        TestAppDependencies::class,
                   ],
    modules = [
        TestAppModule::class,
        TestNetworkModule::class,
        TestNetworkModuleImpl::class,
        DataBaseModule::class
    ]
)
@Singleton
interface TestAppComponent: AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            appDependencies: TestAppDependencies): TestAppComponent
    }
}

object TestAppComponentHolder {
    lateinit var testAppComponent: TestAppComponent
}