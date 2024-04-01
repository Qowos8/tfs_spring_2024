package com.example.homework_2.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_2.databinding.ProfileFragmentBinding
import com.example.homework_2.people.PeopleItem

class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        binding.toolbarProfile.toolbar.visibility = View.GONE
        binding.toolbarProfile.backButton.visibility= View.GONE
        return binding.root
    }
}