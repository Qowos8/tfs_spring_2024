package com.example.homework_2.channels.child.all_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.channels.StreamItem
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.utils.FilterByNamesUtils.filterItemsByName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class AllViewModel : ViewModel() {

    private val _searchState: MutableStateFlow<StreamAllState> =
        MutableStateFlow(StreamAllState.Init)
    val searchState: StateFlow<StreamAllState> get() = _searchState.asStateFlow()

    val currentSearch: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)

    private var allItems: MutableList<StreamItem> = mutableListOf()

    init {
        searchFlow()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchFlow() {
        currentSearch
            .filter { it.isNotEmpty() }
            .debounce(500L)
            .map { it.trim() }
            .mapLatest { query ->
                if (query.isEmpty()) {
                    _searchState.value = StreamAllState.Success(allItems)
                } else {
                    _searchState.value = StreamAllState.Loading
                    delay(500L)
                    _searchState.value =
                        StreamAllState.Success(filterItemsByName(allItems, query))
                }
            }
            .launchIn(viewModelScope)
    }

    fun addMockAll() {
        allItems = mutableListOf(
            StreamItem(
                "general",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 1, parentName = "general"),
                    TopicItem("Bruh", 2, 24, parentId = 1, parentName = "general")
                )
            ),
            StreamItem(
                "Development",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 2, parentName = "Development"),
                    TopicItem("Bruh", 2, 24, parentId = 2, parentName = "Development")
                )
            )
        )
        _searchState.value = StreamAllState.Success(allItems)
    }
}