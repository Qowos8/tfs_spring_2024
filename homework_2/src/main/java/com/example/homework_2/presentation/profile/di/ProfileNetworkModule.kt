package com.example.homework_2.presentation.profile.di

import com.example.homework_2.data.network.api.profile.ProfileApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
object ProfileNetworkModule {

    @Provides
    fun provideApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create<ProfileApi>()
    }
}