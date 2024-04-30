package com.example.homework_2.presentation.people.di

import com.example.homework_2.presentation.people.PeopleFragment
import com.example.homework_2.presentation.profile.di.ProfileNetworkModule
import dagger.Component

@Component(
    dependencies = [
        PeopleDependencies::class
    ],
    modules = [
        PeopleRepositoryModule::class,
        ProfileNetworkModule::class
    ]
)
interface PeopleComponent {
    fun inject(fragment: PeopleFragment)

    @Component.Factory
    interface PeopleFactory{
        fun create(app: PeopleDependencies): PeopleComponent
    }
}

fun PeopleComponent(): PeopleComponent = DaggerPeopleComponent.factory().create(PeopleDependencies.Impl)