package com.example.homework_2

import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.bottom_sheet.EmojiBottomSheetFragment
import com.example.homework_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BottomSheetClickListener {
    private lateinit var binding: ActivityMainBinding
    val messagesList = mutableListOf<MessageItem>()
    private var currentMessageIndex = 0
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            addTextChangedListener()
            sendMessage()
        }
        adapter = MessageAdapter(
            onLongItemClick = { messageItem ->
                openBottomSheet(messageItem)
            },
            onItemClick = { messageItem ->
                openBottomSheet(messageItem)
            },
            messagesList,
            1,
            this@MainActivity
        )
        binding.messageRecycler.adapter = adapter
    }

    private fun ActivityMainBinding.addTextChangedListener() {
        messageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText = s.toString()
                if (inputText.isNotEmpty()) {
                    binding.messageButton.visibility = View.VISIBLE
                    binding.resourceButton.visibility = View.GONE
                } else {
                    binding.messageButton.visibility = View.GONE
                    binding.resourceButton.visibility = View.VISIBLE
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
                        1,
                        sendTime = getSendTime()
                    )
                )
                binding.messageRecycler.adapter = adapter
                Log.d("input", messageInput.text.toString())
                messageInput.text?.clear()
            }
        }
    }

    private fun getSendTime(): String {
        val messageTimestamp: Long = System.currentTimeMillis()

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = messageTimestamp

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        return "$dayOfMonth.$month.$year"
    }

    private fun openBottomSheet(messageItem: MessageItem) {
        val bottomSheetDialogFragment = EmojiBottomSheetFragment()
        bottomSheetDialogFragment.setMessageItem(messageItem)
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onEmojiClicked(emoji: String) {
        val lastMessage = messagesList.lastOrNull()
        if (lastMessage != null) {
            val reactionsMap = lastMessage.reactions
            if (!reactionsMap.containsKey(emoji)) {
                reactionsMap[emoji] = reactionsMap.getOrDefault(emoji, 0)
            } else {
                reactionsMap[emoji] = reactionsMap[emoji]!! + 1
            }

            val position = messagesList.size - 1
            adapter.notifyItemChanged(position)
        }
    }
}