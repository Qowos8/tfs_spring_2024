package com.example.homework_2.presentation.chat.mvi

import vivid.money.elmslie.core.store.ElmStore
import javax.inject.Inject

class ChatStoreFactory @Inject constructor(
    private val reducer: ChatReducer,
    private val actor: ChatActor) {

    private val store by lazy {
        ElmStore(
            initialState = ChatHolderState(),
            reducer = reducer,
            actor = actor
        )
    }

    fun provide() = store

}