package com.example.homework_2.presentation.people.mvi

import vivid.money.elmslie.core.store.ElmStore

class PeopleStoreFactory(private val actor: PeopleActor) {

    private val store by lazy {
        ElmStore(
            initialState = PeopleState.Init,
            reducer = PeopleReducer(),
            actor = actor
        )
    }

    fun provide() = store

}