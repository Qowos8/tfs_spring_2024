package com.example.homework_2.di

import com.example.homework_2.presentation.chat.di.ChatDependencies
import retrofit2.Retrofit

class TestChatDependencies(override val retrofit: Retrofit) : ChatDependencies{
}
