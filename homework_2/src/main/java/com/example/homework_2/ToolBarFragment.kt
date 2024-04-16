package com.example.homework_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_2.databinding.ToolbarFragmentBinding

class ToolBarFragment : Fragment() {
    private lateinit var binding: ToolbarFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ToolbarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}