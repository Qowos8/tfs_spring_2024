package com.example.homework_2.presentation.people

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.databinding.PeopleFragmentBinding
import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.presentation.people.di.PeopleComponent
import com.example.homework_2.presentation.people.mvi.PeopleState
import com.example.homework_2.presentation.people.mvi.PeopleStoreFactory
import com.example.homework_2.presentation.people.search.PeopleSearchState
import com.example.homework_2.presentation.people.search.PeopleViewModel
import com.example.homework_2.presentation.people.search.PeopleViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleFragment : Fragment() {

    @Inject
    lateinit var factory: PeopleStoreFactory

    @Inject
    lateinit var viewModelFactory: PeopleViewModelFactory

    private lateinit var binding: PeopleFragmentBinding

    private val viewModel: PeopleViewModel by viewModels {
        viewModelFactory
    }

    private val peopleAdapter: PeopleAdapter by lazy {
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
        binding.setupRecycler()
        render()
        binding.sendQuery()
        collectResultQuery()
    }

    private fun render() {
        viewModel.profileState.onEach { state ->
            when (state) {
                PeopleState.Init -> Unit

                PeopleState.Loading -> Unit

                is PeopleState.Error -> {
                    Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT).show()
                }

                is PeopleState.CacheSuccess -> {
                    peopleAdapter.submitList(state.users) {
                        binding.peopleRecycler.scrollToPosition(0)
                    }
                    if (peopleAdapter.currentList.isEmpty() || peopleAdapter.currentList.size <= 1) {
                        viewModel.updatePeople()
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun PeopleFragmentBinding.sendQuery() {
        usersInput.addTextChangedListener {
            lifecycleScope.launch {
                it.let { query ->
                    if (query != null) {
                        if (query.isNotEmpty()){
                            viewModel.currentSearch.emit(query.toString())
                        }
                    }
                }
            }
        }
    }

    private fun collectResultQuery() {
        viewModel.queryState.onEach { state ->
            when (state) {
                is PeopleSearchState.Error -> Snackbar.make(
                    binding.root,
                    state.error,
                    Snackbar.LENGTH_SHORT
                ).show()

                PeopleSearchState.Init -> Unit
                is PeopleSearchState.Result -> {
                    viewModel.search(state.query)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun PeopleFragmentBinding.setupRecycler() {
        peopleRecycler.apply {
            adapter = peopleAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            ).apply {
                stackFromEnd = true
            }
        }
    }

    private fun openProfile(item: ProfileItem) {
        (activity as OnUserClickListener).onUserClicked(item.id)
    }
}