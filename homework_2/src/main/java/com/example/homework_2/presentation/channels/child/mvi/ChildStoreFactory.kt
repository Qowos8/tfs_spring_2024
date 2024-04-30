package com.example.homework_2.presentation.channels.child.mvi

import vivid.money.elmslie.core.store.ElmStore
import javax.inject.Inject

class ChildStoreFactory @Inject constructor(
    private val reducer: ChildReducer,
    private val actor: ChildActor) {

    private val store by lazy {
        ElmStore(
            initialState = ChildState.Init,
            reducer = reducer,
            actor = actor
        )
    }

    fun provide() = store

}