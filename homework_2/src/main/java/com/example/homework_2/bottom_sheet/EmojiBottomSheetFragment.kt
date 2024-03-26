package com.example.homework_2.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_2.BottomSheetClickListener
import com.example.homework_2.MessageItem
import com.example.homework_2.emojiSetNCU
import com.example.homework_2.databinding.BottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmojiBottomSheetFragment: BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogFragmentBinding
    private var selectedMessageItem: MessageItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetDialogFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = emojiSetNCU.take(42).map { it.getCodeString() }
        val layoutManager = GridLayoutManager(requireContext(),7)
        val adapter = EmojiBottomSheetAdapter(data)
        binding.apply {
            bottomSheetRecycler.adapter = adapter
            adapter.setEmojiClickListener(object: BottomSheetClickListener {
                override fun onEmojiClicked(emoji: String) {
                    (activity as? BottomSheetClickListener)?.onEmojiClicked(emoji)
                    dismiss()                }
            })
            bottomSheetRecycler.layoutManager = layoutManager
        }
    }

    fun setMessageItem(messageItem: MessageItem) {
        selectedMessageItem = messageItem
    }
}