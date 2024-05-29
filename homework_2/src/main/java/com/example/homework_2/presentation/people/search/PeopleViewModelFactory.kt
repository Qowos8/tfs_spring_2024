package com.example.homework_2.presentation.people.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework_2.presentation.people.mvi.PeopleStoreFactory
import javax.inject.Inject

class PeopleViewModelFactory @Inject constructor(
    private val peopleStore: PeopleStoreFactory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleViewModel(peopleStore.provide()) as T
    }
}