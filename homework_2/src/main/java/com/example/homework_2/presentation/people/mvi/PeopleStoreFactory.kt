package com.example.homework_2.presentation.people.mvi

import vivid.money.elmslie.core.store.ElmStore
import javax.inject.Inject

class PeopleStoreFactory @Inject constructor(
    private val reducer: PeopleReducer,
    private val actor: PeopleActor,
) {

    private val store by lazy {
        ElmStore(
            initialState = PeopleState.Init,
            reducer = reducer,
            actor = actor
        )
    }

    fun provide() = store

}