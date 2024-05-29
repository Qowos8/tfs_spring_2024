package com.example.homework_2.presentation.profile.di

import com.example.homework_2.presentation.profile.another.AnotherProfileActivity
import com.example.homework_2.presentation.profile.me.ProfileFragment
import com.example.homework_2.presentation.profile.me.state_holder.ProfileViewModel
import dagger.Component

@Component(
    dependencies = [
        ProfileDependencies::class,
    ],
    modules = [
        ProfileRepositoryModule::class,
        ProfileNetworkModule::class,
        ProfileDBModule::class,
    ]
)
interface ProfileComponent {
    fun inject(fragment: ProfileFragment)
    fun inject(activity: AnotherProfileActivity)

    @Component.Factory
    interface ProfileFactory{
        fun create(app: ProfileDependencies): ProfileComponent
    }
}

fun ProfileComponent(): ProfileComponent =
    DaggerProfileComponent.factory().create(ProfileDependencies.Impl)