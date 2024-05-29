package com.example.homework_2.presentation.channels.child.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework_2.presentation.channels.child.mvi.ChannelsStoreFactory
import javax.inject.Inject

class ChannelsViewModelFactory @Inject constructor(
    private val channelsStore: ChannelsStoreFactory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChannelsViewModel(channelsStore.provide()) as T
    }
}