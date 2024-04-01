package com.example.homework_2.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_2.chat.ChatActivity
import com.example.homework_2.chat.MessageItem
import com.example.homework_2.view.emojiSetNCU
import com.example.homework_2.databinding.BottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmojiBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogFragmentBinding
    private var selectedMessageItem: MessageItem? = null
    private lateinit var chatActivity: ChatActivity
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

        //chatFragment = parentFragmentManager.findFragmentById(R.id.channels_container) as ChatFragment
        val data = emojiSetNCU.take(42).map { it.getCodeString() }
        val layoutManager = GridLayoutManager(requireContext(), 7)
        val adapter = EmojiBottomSheetAdapter(data, selectedMessageItem!!)
        binding.apply {
            bottomSheetRecycler.adapter = adapter
            adapter.setEmojiClickListener(object : BottomSheetClickListener {
                override fun onEmojiClicked(messageItem: MessageItem, emoji: String) {
                    (activity as BottomSheetClickListener).onEmojiClicked(messageItem, emoji)
                    dismiss()
                }
            })
            bottomSheetRecycler.layoutManager = layoutManager
        }
    }

    fun setMessageItem(messageItem: MessageItem) {
        selectedMessageItem = messageItem
    }
}