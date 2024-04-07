package com.example.homework_2.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.MainActivity
import com.example.homework_2.R
import com.example.homework_2.bottom_sheet.BottomSheetClickListener
import com.example.homework_2.bottom_sheet.EmojiBottomSheetFragment
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.chat.delegate.CompanionMessageDelegate
import com.example.homework_2.chat.delegate.UserMessageDelegate
import com.example.homework_2.databinding.ChatFragmentBinding
import com.example.homework_2.databinding.ToolbarFragmentBinding
import com.example.homework_2.delegate.MainAdapter
import com.example.homework_2.utils.CalendarUtils
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class ChatActivity : AppCompatActivity(), BottomSheetClickListener {
    private lateinit var binding: ChatFragmentBinding
    private val messagesList = mutableListOf<MessageItem>()
    private var currentMessageIndex = 0
    private lateinit var topicItem: TopicItem
    private lateinit var messageAdapter: MessageAdapter
    private val mainAdapter: MainAdapter by lazy(LazyThreadSafetyMode.NONE) { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topicItem = MainActivity.DataHolder.topicData!!
        val itemDecoration = ItemDecoration(this, R.drawable.divider, getDateString())

        binding.apply {
            addTextChangedListener()
            sendMessage()
        }
        setAdapter()
        binding.messageRecycler.adapter = mainAdapter
        messageAdapter = MessageAdapter(
            onLongItemClick = { messageItem ->
                openBottomSheet(messageItem)
            },
            onItemClick = { messageItem ->
                openBottomSheet(messageItem)
            },
            this
        )
        binding.toolbar.apply {
            setToolBar(this)
        }
        binding.apply {
            topicName.text = topicItem.name
            messageRecycler.apply {
                itemAnimator = null
                addItemDecoration(itemDecoration)
                adapter = messageAdapter
            }
        }
    }

    private fun ChatFragmentBinding.addTextChangedListener() {
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

    private fun ChatFragmentBinding.sendMessage() {
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
                //mainAdapter.submitList()
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

    private fun getDateString(): String {
        val currentDate = Calendar.getInstance()
        return "${currentDate.get(Calendar.DAY_OF_MONTH)} ${
            CalendarUtils.stringMonth(
                currentDate.get(
                    Calendar.MONTH
                )
            )
        }"
    }

    private fun setToolBar(binding: ToolbarFragmentBinding) {
        binding.toolbar.title = topicItem.parentName
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
        val defaultEmojiService = DefaultEmojiService()
        mainAdapter.addDelegate(
            UserMessageDelegate(
                defaultEmojiService,
                onLongItemClick = { messageItem -> openBottomSheet(messageItem) },
                onItemClick = { messageItem -> openBottomSheet(messageItem) },
                this
            )
        )

        mainAdapter.addDelegate(
            CompanionMessageDelegate(
                defaultEmojiService,
                onLongItemClick = { messageItem -> openBottomSheet(messageItem) },
                onItemClick = { messageItem -> openBottomSheet(messageItem) },
                this
            )
        )
    }
}
