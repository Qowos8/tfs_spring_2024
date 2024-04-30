package com.example.homework_2.presentation.chat.di

import com.example.homework_2.presentation.chat.ChatActivity
import dagger.Component

@Component(
    dependencies = [
        ChatDependencies::class
    ],
    modules = [
        ChatRepositoryModule::class,
        ChatNetworkModule::class
    ]
)
interface ChatComponent {
    fun inject(activity: ChatActivity)

    @Component.Factory
    interface ChatFactory{
        fun create(app: ChatDependencies): ChatComponent
    }
}

fun ChatComponent(): ChatComponent = DaggerChatComponent.factory().create(ChatDependencies.Impl)