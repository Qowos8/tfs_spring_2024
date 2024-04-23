package com.example.homework_2.presentation.channels.child.mvi

import vivid.money.elmslie.core.store.ElmStore

class ChildStoreFactory(private val actor: ChildActor) {

    private val store by lazy {
        ElmStore(
            initialState = ChildState.Init,
            reducer = ChildReducer(),
            actor = actor
        )
    }

    fun provide() = store

}