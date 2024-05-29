package com.example.homework_2.presentation.people.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.presentation.people.mvi.PeopleEffect
import com.example.homework_2.presentation.people.mvi.PeopleEvent
import com.example.homework_2.presentation.people.mvi.PeopleState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import vivid.money.elmslie.core.store.Store

class PeopleViewModel(
    private val peopleStore: Store<
            PeopleEvent,
            PeopleEffect,
            PeopleState>
) : ViewModel() {

    val currentSearch: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)

    private val _queryState: MutableStateFlow<PeopleSearchState> = MutableStateFlow(PeopleSearchState.Init)
    val queryState: StateFlow<PeopleSearchState> get() = _queryState.asStateFlow()

    val profileState: StateFlow<PeopleState> = peopleStore.states

    init {
        loadPeople()
        searchFlow()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchFlow() {
        currentSearch
            .debounce(700L)
            .map { it.trim() }
            .mapLatest { query -> _queryState.emit(PeopleSearchState.Result(query)) }
            .launchIn(viewModelScope)
    }

    fun loadPeople() {
        peopleStore.accept(PeopleEvent.Ui.Init)
    }

    fun updatePeople() {
        peopleStore.accept(PeopleEvent.Ui.LoadUsers)
    }

    fun search(query: String){
        peopleStore.accept(PeopleEvent.Ui.SearchStream(query))
    }
}
