package com.example.homework_2.presentation.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_2.databinding.SkeletonComponentBinding

class SkeletonFragment: Fragment() {

    private lateinit var binding: SkeletonComponentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SkeletonComponentBinding.inflate(layoutInflater)

        binding.shimmerContainer.startShimmer()

        return binding.root
    }
}