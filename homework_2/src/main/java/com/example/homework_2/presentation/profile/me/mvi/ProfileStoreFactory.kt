package com.example.homework_2.presentation.profile.me.mvi

import vivid.money.elmslie.core.store.ElmStore
import vivid.money.elmslie.core.store.Store

class ProfileStoreFactory(private val actor: ProfileActor) {

    private val store by lazy {
        ElmStore(
            initialState = ProfileState.Init,
            reducer = ProfileReducer(),
            actor = actor
        )
    }

    fun provide() = store

}

