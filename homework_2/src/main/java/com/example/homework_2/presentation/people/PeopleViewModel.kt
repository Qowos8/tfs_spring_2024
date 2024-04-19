package com.example.homework_2.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PeopleViewModel: ViewModel() {
    private val _peopleState: MutableStateFlow<PeopleState> = MutableStateFlow(PeopleState.Init)
    val peopleState: StateFlow<PeopleState> get() = _peopleState.asStateFlow()

    private val _onlineState = MutableStateFlow<OnlineState>(OnlineState.Init)
    val onlineState: StateFlow<OnlineState> get() = _onlineState.asStateFlow()

    fun getUsers(){
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getUsers()
                _peopleState.emit(PeopleState.Success(response.members))
            }.onFailure {
                _peopleState.emit(PeopleState.Error(it.message.toString()))
            }
        }
    }

    fun getStatus(){
        viewModelScope.launch{
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getPresence()
                _onlineState.emit(OnlineState.Success(response))
            }.onFailure {
                _onlineState.emit(OnlineState.Error(it.message.toString()))
            }
        }
    }
}