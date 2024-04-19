package com.example.homework_2.presentation.channels.child

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.data.network.model.AllStreamItem
import com.example.homework_2.data.network.model.TopicState
import com.example.homework_2.utils.FilterByNamesUtils
import com.example.homework_2.utils.runCatchingNonCancellation
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
import kotlinx.coroutines.launch

class ChildViewModel: ViewModel() {
    private val _searchState: MutableStateFlow<ChildState> = MutableStateFlow(ChildState.Init)
    val searchState: StateFlow<ChildState> get() = _searchState.asStateFlow()

    val currentSearch: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 1)
    private var allItems: List<AllStreamItem> = mutableListOf()

    private val _topicState = MutableStateFlow<TopicState>(TopicState.Init)
    val topicState: StateFlow<TopicState> get() = _topicState

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
            _searchState.value = (ChildState.Success(allItems))
        }
        else if(query.length > 5) {
            _searchState.value = (ChildState.Error("Too much symbols"))
        }
        else {
            _searchState.value = (ChildState.Loading)
            delay(700L)
            _searchState.value = (
                ChildState.Success(FilterByNamesUtils.filterItemsByName(allItems, query)))
        }
    }

    fun subscribeStreamsResponse(){
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getSubStreams()
                allItems = response.subscriptions
                _searchState.emit(ChildState.Success(response.subscriptions))
            }.onFailure {
                _searchState.emit(ChildState.Error(it.message.toString()))
            }
        }
    }

    fun allStreamsResponse(){
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getAllStreams()
                allItems = response.subscriptions
                _searchState.emit(ChildState.Success(response.subscriptions))
            }.onFailure {
                _searchState.emit(ChildState.Error(it.message.toString()))
            }
        }
    }

    fun getTopics(streamId: Int){
        viewModelScope.launch {
            _topicState.emit(TopicState.Loading)
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getTopics(streamId)
                _topicState.emit(TopicState.Success(response.topics))
                Log.d("response", response.toString())
            }.onFailure {
                _topicState.emit(TopicState.Error(it.message.toString()))
                it.printStackTrace()
            }
        }
    }
}