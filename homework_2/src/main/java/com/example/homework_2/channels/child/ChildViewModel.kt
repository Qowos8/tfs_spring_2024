package com.example.homework_2.channels.child

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.channels.StreamItem
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.utils.FilterByNamesUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class ChildViewModel: ViewModel() {
    private val _searchState: MutableStateFlow<ChildState> = MutableStateFlow(ChildState.Init)
    val searchState: StateFlow<ChildState> get() = _searchState.asStateFlow()

    val currentSearch: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)
    private var allItems: MutableList<StreamItem> = mutableListOf()

    init {
        searchFlow()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchFlow() {
        currentSearch
            .debounce(700L)
            .map { it.trim() }
            .mapLatest { query -> emitState(query) }
            .launchIn(viewModelScope)
    }

    private suspend fun emitState(query: String){
        if (query.isEmpty()) {
            _searchState.value = ChildState.Success(allItems)
        }
        else if(query.length > 5) {
            _searchState.value = ChildState.Error("Too much symbols")
        }
        else {
            _searchState.value = ChildState.Loading
            delay(700L)
            _searchState.value =
                ChildState.Success(FilterByNamesUtils.filterItemsByName(allItems, query))
        }
    }

    fun addMockAll() {
        allItems = mutableListOf(
            StreamItem(
                0,
                "general",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 1, parentName = "general"),
                    TopicItem("Bruh", 2, 24, parentId = 1, parentName = "general")
                )
            ),
            StreamItem(
                1,
                "Development",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 2, parentName = "Development"),
                    TopicItem("Bruh", 2, 24, parentId = 2, parentName = "Development")
                )
            ),
            StreamItem(
                3,
                "Test",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 2, parentName = "Development"),
                    TopicItem("Bruh", 2, 24, parentId = 2, parentName = "Development")
                )
            )
        )
        _searchState.value = ChildState.Success(allItems)
    }

    fun addMockSubscribe() {
        allItems = mutableListOf(
            StreamItem(
                1,
                "general",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 1, parentName = "general"),
                    TopicItem("Bruh", 2, 24, parentId = 1, parentName = "general")
                )
            ),
            StreamItem(
                2,
                "Development",
                true,
                mutableListOf(
                    TopicItem("Testing", 1, 1023, parentId = 2, parentName = "Development"),
                    TopicItem("Bruh", 2, 24, parentId = 2, parentName = "Development")
                )
            )
        )
        _searchState.value = ChildState.Success(allItems)
    }
}