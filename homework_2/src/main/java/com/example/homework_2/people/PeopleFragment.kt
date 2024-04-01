package com.example.homework_2.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_2.databinding.PeopleFragmentBinding

class PeopleFragment : Fragment() {
    private lateinit var binding: PeopleFragmentBinding
    private lateinit var peoples: MutableList<PeopleItem>
    private val adapter: PeopleAdapter by lazy {
        PeopleAdapter(::openProfile)
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
        addMock()
        binding.peopleRecycler.adapter = adapter
        adapter.update(peoples)
    }

    private fun openProfile(item: PeopleItem) {
        (activity as OnUserClickListener).onUserClicked(item)
    }

    private fun addMock() {
        peoples = mutableListOf(
            PeopleItem(
                1,
                "SomeUser",
                "someuser@gmail.com",
                true,
                true
            ),
            PeopleItem(
                2,
                "SomeUser2",
                "someuser2@gmail.com",
                false,
                false
            )
        )
    }
}