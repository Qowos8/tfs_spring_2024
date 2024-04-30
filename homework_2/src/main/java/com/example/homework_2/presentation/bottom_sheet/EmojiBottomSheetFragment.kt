package com.example.homework_2.presentation.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_2.databinding.BottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmojiBottomSheetFragment : BottomSheetDialogFragment(), BottomSheetClickListener {

    private lateinit var binding: BottomSheetDialogFragmentBinding
    private val adapter: EmojiBottomSheetAdapter by lazy {
        EmojiBottomSheetAdapter(::onEmojiClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetDialogFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 7)

        binding.apply {
            bottomSheetRecycler.adapter = adapter
            bottomSheetRecycler.layoutManager = layoutManager
        }
    }

    override fun onEmojiClicked(emoji: String) {
        (activity as BottomSheetClickListener).onEmojiClicked(emoji)
        dismiss()
    }
}