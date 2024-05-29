package com.example.homework_2.presentation.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_2.databinding.BottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmojiBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDialogFragmentBinding
    private val emojiAdapter: EmojiBottomSheetAdapter by lazy {
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

        binding.bottomSheetRecycler.apply {
            adapter = emojiAdapter
            layoutManager = GridLayoutManager(requireContext(), 7)
        }
    }

    private fun onEmojiClicked(emoji: String) {
        val messageId = arguments?.getInt(SELECTED_MESSAGE)
        if (messageId != null){
            (activity as BottomSheetClickListener).onEmojiClicked(emoji, messageId)
        }
        dismiss()
    }

    private companion object{
        private const val SELECTED_MESSAGE = "selected"
    }
}