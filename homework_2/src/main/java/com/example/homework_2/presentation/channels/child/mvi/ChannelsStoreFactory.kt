package com.example.homework_2.presentation.channels.child.mvi

import vivid.money.elmslie.core.store.ElmStore
import javax.inject.Inject

class ChannelsStoreFactory @Inject constructor(
    private val reducer: ChannelsReducer,
    private val actor: ChannelsActor
) {

    private val store by lazy {
        ElmStore(
            initialState = ChannelsState.Init,
            reducer = reducer,
            actor = actor
        )
    }

    fun provide() = store

}