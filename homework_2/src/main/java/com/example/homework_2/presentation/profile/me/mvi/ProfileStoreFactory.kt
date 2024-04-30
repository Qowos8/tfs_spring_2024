package com.example.homework_2.presentation.profile.me.mvi

import vivid.money.elmslie.core.store.ElmStore
import javax.inject.Inject

class ProfileStoreFactory @Inject constructor(
    private val reducer: ProfileReducer,
    private val actor: ProfileActor,
) {

    private val store by lazy {
        ElmStore(
            initialState = ProfileState.Init,
            reducer = reducer,
            actor = actor,
        )
    }

    fun provide() = store
}

