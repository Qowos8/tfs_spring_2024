package com.example.homework_2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.bottom_sheet.EmojiBottomSheetFragment
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.utils.CalendarUtils.stringMonth
import java.util.Calendar


class MainActivity : AppCompatActivity(), BottomSheetClickListener {
    private lateinit var binding: ActivityMainBinding
    private val messagesList = mutableListOf<MessageItem>()
    private var currentMessageIndex = 0
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemDecoration = ItemDecoration(this, R.drawable.divider, getDateString())

        binding.apply {
            addTextChangedListener()
            sendMessage()
        }
        messageAdapter = MessageAdapter(
            onLongItemClick = { messageItem ->
                openBottomSheet(messageItem)
            },
            onItemClick = { messageItem ->
                openBottomSheet(messageItem)
            },
            this@MainActivity
        )

        binding.messageRecycler.apply {
            itemAnimator = null
            addItemDecoration(itemDecoration)
            adapter = messageAdapter
        }

    }

    private fun ActivityMainBinding.addTextChangedListener() {
        messageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText = s.toString()
                if (inputText.isNotEmpty()) {
                    messageButton.visibility = View.VISIBLE
                    resourceButton.visibility = View.GONE
                } else {
                    messageButton.visibility = View.GONE
                    resourceButton.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun ActivityMainBinding.sendMessage() {
        if (messageButton.isEnabled) {
            messageButton.setOnClickListener {
                messagesList.add(
                    MessageItem(
                        currentMessageIndex,
                        messageInput.text.toString(),
                        1
                    )
                )
                currentMessageIndex++
                messageAdapter.submitList(messagesList.toList())
                messageInput.text?.clear()
            }
        }
    }

    private fun openBottomSheet(messageItem: MessageItem) {
        val bottomSheetDialogFragment = EmojiBottomSheetFragment()
        bottomSheetDialogFragment.setMessageItem(messageItem)
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onEmojiClicked(messageItem: MessageItem, emoji: String) {
        val position = messagesList.indexOf(messageItem)
        if (position != -1) {
            val lastMessage = messagesList[position]
            val reactionsMap = lastMessage.reactions
            if (!reactionsMap.containsKey(emoji)) {
                reactionsMap[emoji] = reactionsMap.getOrDefault(emoji, 0)
            } else {
                reactionsMap[emoji] = reactionsMap[emoji]!! + 1
            }
            messageAdapter.notifyItemChanged(position)
        }
    }
    private fun getDateString():String {
        val currentDate = Calendar.getInstance()
        return "${currentDate.get(Calendar.DAY_OF_MONTH)} ${stringMonth(currentDate.get(Calendar.MONTH))}"
    }
}