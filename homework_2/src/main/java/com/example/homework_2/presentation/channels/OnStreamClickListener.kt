package com.example.homework_2.presentation.channels

import com.example.homework_2.data.network.model.AllStreamItem

interface OnStreamClickListener {

    fun onStreamClick(streamItem: AllStreamItem)

}