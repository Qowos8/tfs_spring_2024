package com.example.homework_2.presentation.channels

import com.example.homework_2.data.network.model.channels.stream.StreamItemApi

interface OnStreamClickListener {

    fun onStreamClick(streamItem: StreamItemApi)

}