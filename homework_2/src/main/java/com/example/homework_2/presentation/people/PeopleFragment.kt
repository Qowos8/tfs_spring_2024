package com.example.homework_2.presentation.people

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework_2.R
import com.example.homework_2.databinding.PeopleFragmentBinding
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.presentation.base.ElmBaseFragment
import com.example.homework_2.presentation.people.di.PeopleComponent
import com.example.homework_2.presentation.people.mvi.PeopleActor
import com.example.homework_2.presentation.people.mvi.PeopleEffect
import com.example.homework_2.presentation.people.mvi.PeopleEvent
import com.example.homework_2.presentation.people.mvi.PeopleState
import com.example.homework_2.presentation.people.mvi.PeopleStoreFactory
import com.google.android.material.snackbar.Snackbar
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class PeopleFragment : ElmBaseFragment<
        PeopleEvent,
        PeopleEffect,
        PeopleState>(R.layout.people_fragment) {

    @Inject
    lateinit var factory: PeopleStoreFactory

    private lateinit var binding: PeopleFragmentBinding

    private var isCreate = false
    private var isErrorShowed = false
    private var isFirstLoad = true

    override val store: Store<PeopleEvent, PeopleEffect, PeopleState>
        by elmStoreWithRenderer(elmRenderer = this){
            factory.provide()
        }

    override fun render(state: PeopleState) {
        trackState(state)
    }

    private val adapter: PeopleAdapter by lazy {
        PeopleAdapter(::openProfile)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PeopleComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = PeopleFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.peopleRecycler.adapter = adapter
        binding.peopleRecycler.itemAnimator = null
        if (!isCreate) store.accept(PeopleEvent.Ui.Init)
    }

    private fun openProfile(item: ProfileItem) {
        (activity as OnUserClickListener).onUserClicked(item.id)
    }

    private fun trackState(state: PeopleState) {
        when (state) {
            is PeopleState.Error -> {
                if (!isErrorShowed){
                    Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
                    isErrorShowed = true
                }
            }

            PeopleState.Init -> {

            }

            PeopleState.Loading -> {

            }

            is PeopleState.Success -> {
                adapter.submitList(state.users){
                    if (isFirstLoad) {
                        binding.peopleRecycler.scrollToPosition(0)
                        isFirstLoad = false
                    }
                }
            }

            is PeopleState.CacheSuccess -> {
                adapter.submitList(state.users){
                    binding.peopleRecycler.scrollToPosition(0)
                }
                if(!isCreate) {
                    store.accept(PeopleEvent.Ui.LoadUsers)
                    isCreate = true
                }
            }
        }
    }

    private companion object{
        private const val CREATE_KEY = "created"
    }
}