package com.example.homework_2.presentation.channels.child.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class ChildViewModel : ViewModel() {
    val currentSearch: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)
    private val _queryState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState.Init)
    val queryState: StateFlow<SearchState> get() = _queryState.asStateFlow()

    init {
        searchFlow()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchFlow() {
        currentSearch
            .debounce(700L)
            .map { it.trim() }
            .mapLatest { query -> _queryState.emit(SearchState.Result(query)) }
            .launchIn(viewModelScope)
    }
}