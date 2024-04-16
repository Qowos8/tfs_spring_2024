package com.example.homework_2.people

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework_2.databinding.PeopleFragmentBinding
import com.example.homework_2.profile.ProfileItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PeopleFragment : Fragment() {
    private lateinit var binding: PeopleFragmentBinding
    private lateinit var peoples: MutableList<ProfileItem>
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
    }

    private fun openProfile(item: ProfileItem) {
        (activity as OnUserClickListener).onUserClicked(item)
    }

    private fun trackState(){
        lifecycleScope.launch {
            viewModel.peopleState.collect{ state ->
                when(state){
                    is PeopleState.Error -> {
                        Log.d("peopl", state.error)
                    }
                    PeopleState.Init -> {
                        Log.d("peopl", "init")

                    }
                    PeopleState.Loading -> {
                        Log.d("peopl", "Loading")

                    }
                    is PeopleState.Success -> {
                        Log.d("peopl", "success")
                        adapter.update(state.users)
                    }
                }
            }
        }
    }

}