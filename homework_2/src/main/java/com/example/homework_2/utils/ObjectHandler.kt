package com.example.homework_2.utils

import kotlinx.coroutines.flow.MutableSharedFlow

object ObjectHandler {
    private val dataFlow: MutableSharedFlow<String> = MutableSharedFlow()

    suspend fun sendFlow(streams: String){
        dataFlow.emit(streams)
    }

    fun getFlow(): MutableSharedFlow<String> {
        return dataFlow
    }
}