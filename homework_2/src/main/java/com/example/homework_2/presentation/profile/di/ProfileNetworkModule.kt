package com.example.homework_2.presentation.profile.di

import com.example.homework_2.data.network.api.ProfileApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
internal object ProfileNetworkModule {

    @Provides
    fun provideApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create<ProfileApi>()
    }
}