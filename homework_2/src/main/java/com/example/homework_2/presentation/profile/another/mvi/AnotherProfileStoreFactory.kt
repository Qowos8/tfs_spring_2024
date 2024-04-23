package com.example.homework_2.presentation.profile.another.mvi

import vivid.money.elmslie.core.store.ElmStore

class AnotherProfileStoreFactory(private val actor: AnotherProfileActor) {

    private val store by lazy {
        ElmStore(
            initialState = AnotherProfileState.Init,
            reducer = AnotherProfileReducer(),
            actor = actor
        )
    }

    fun provide() = store

}