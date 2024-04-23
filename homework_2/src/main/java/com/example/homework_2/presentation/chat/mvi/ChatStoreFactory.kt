package com.example.homework_2.presentation.chat.mvi

import vivid.money.elmslie.core.store.ElmStore

class ChatStoreFactory(private val actor: ChatActor) {

    private val store by lazy {
        ElmStore(
            initialState = ChatState.Init,
            reducer = ChatReducer(),
            actor = actor
        )
    }

    fun provide() = store

}