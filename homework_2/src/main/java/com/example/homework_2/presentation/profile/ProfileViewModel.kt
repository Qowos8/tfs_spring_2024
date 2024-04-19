package com.example.homework_2.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.data.network.di.RetrofitModule
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val profileState: StateFlow<ProfileState> get() = _profileState.asStateFlow()

    fun getUser(){
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getUserMe()
                _profileState.emit(ProfileState.Success(response))
            }.onFailure {
                _profileState.emit(ProfileState.Error(it.message.toString()))
            }
        }
    }
}