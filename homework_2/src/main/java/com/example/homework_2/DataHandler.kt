package com.example.homework_2

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.flow.MutableSharedFlow

class DataHandler: Handler(Looper.getMainLooper()) {
    private val dataFlow: MutableSharedFlow<String> = MutableSharedFlow()

    suspend fun sendFlow(streams: String){
        dataFlow.emit(streams)
    }

    fun getFlow(): MutableSharedFlow<String>{
        return dataFlow
    }
}