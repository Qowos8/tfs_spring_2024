package com.example.homework_2.presentation.people

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.databinding.PeopleFragmentBinding
import com.example.homework_2.data.network.model.ProfileItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PeopleFragment : Fragment() {
    private lateinit var binding: PeopleFragmentBinding
    private val viewModel: PeopleViewModel by viewModels()
    private val adapter: PeopleAdapter by lazy {
        PeopleAdapter(::openProfile)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = PeopleFragmentBinding.inflate(layoutInflater)
        viewModel.getUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.peopleRecycler.adapter = adapter
        trackState()
        trackTime()
    }

    private fun openProfile(item: ProfileItem) {
        (activity as OnUserClickListener).onUserClicked(item.id)
    }

    private fun trackState() {
        viewModel.peopleState.onEach { state ->
            when (state) {
                is PeopleState.Error -> {
                    Log.d("peopl", state.error)
                }
                PeopleState.Init -> {

                }
                PeopleState.Loading -> {

                }
                is PeopleState.Success -> {
                    adapter.update(state.users)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun trackTime() {
        viewModel.onlineState.onEach { state ->
            when (state) {
                is OnlineState.Error -> {
                }

                OnlineState.Init -> {
                }

                OnlineState.Loading -> {

                }

                is OnlineState.Success -> {
                    adapter.setStatus(state.list)
                }
            }
        }.launchIn(lifecycleScope)
    }
}