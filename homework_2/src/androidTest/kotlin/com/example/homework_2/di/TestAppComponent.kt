package com.example.homework_2.di

import com.example.homework_2.Application
import com.example.homework_2.di.app.AppDependencies
import com.example.homework_2.di.app.AppModule
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.presentation.chat.di.ChatDependencies
import com.example.homework_2.presentation.chat.di.ChatRepositoryModule
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(
    dependencies = [
        AppDependencies::class,
    ],
    modules = [
        AppModule::class,
        TestNetworkModule::class,
        //ChatRepositoryModule::class,
    ]
)
@Singleton
interface TestAppComponent {
    fun retrofit(): Retrofit
    fun inject(context: android.app.Application)

    @Component.Factory
    interface TestAppComponentFactory{
        fun create(app: TestChatDependencies): TestAppComponent
    }
}

object TestAppComponentHolder {
    lateinit var testAppComponent: TestAppComponent
}

fun TestAppComponent(application: Application): TestAppComponent {
    val testAppDependencies = TestDependencyImpl(application)
    return DaggerTestAppComponent.factory().create(testAppDependencies)
}