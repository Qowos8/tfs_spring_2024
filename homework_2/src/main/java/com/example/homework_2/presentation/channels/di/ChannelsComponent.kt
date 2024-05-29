package com.example.homework_2.presentation.channels.di

import com.example.homework_2.presentation.channels.child.ChannelsFragment
import dagger.Component

@Component(
    dependencies = [
        ChannelsDependencies::class,
    ],
    modules = [
        ChannelsRepositoryModule::class,
        ChannelsNetworkModule::class,
        ChannelsDBModule::class
    ]
)
interface ChannelsComponent {
    fun inject(fragment: ChannelsFragment)

    @Component.Factory
    interface ChannelsFactory{
        fun create(app: ChannelsDependencies): ChannelsComponent
    }
}

fun ChannelsComponent(): ChannelsComponent =
    DaggerChannelsComponent.factory().create(ChannelsDependencies.Impl)