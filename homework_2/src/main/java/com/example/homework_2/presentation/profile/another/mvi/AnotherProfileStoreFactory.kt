package com.example.homework_2.presentation.profile.another.mvi

import vivid.money.elmslie.core.store.ElmStore
import javax.inject.Inject

class AnotherProfileStoreFactory @Inject constructor(
    private val reducer: AnotherProfileReducer,
    private val actor: AnotherProfileActor) {

    private val store by lazy {
        ElmStore(
            initialState = AnotherProfileState.Init,
            reducer = reducer,
            actor = actor
        )
    }

    fun provide() = store

}