package com.example.homework_2.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.network.RetrofitModule
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnotherProfileActivityViewModel: ViewModel() {
    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> get() = _userState.asStateFlow()

    fun getUser(userId: Int){
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.getUser(userId)
                _userState.emit(UserState.Success(response.user))
            }.onFailure {
                _userState.emit(UserState.Error(it.message.toString()))
            }
        }
    }
}