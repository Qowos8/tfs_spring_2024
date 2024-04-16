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
import com.example.homework_2.chat.delegate.DefaultEmojiService
import com.example.homework_2.chat.delegate.UserMessageDelegate
import com.example.homework_2.databinding.ChatFragmentBinding
import com.example.homework_2.databinding.ToolbarFragmentBinding
import com.example.homework_2.delegate.MainAdapter
import com.example.homework_2.utils.DefaultEmoji
import com.example.homework_2.utils.MessageMapper.convertToDelegate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity(), BottomSheetClickListener {
    private lateinit var binding: ChatFragmentBinding
    private val messagesList = mutableListOf<MessageItem>()
    private var currentMessageIndex = 0
    private var selectedMessageIndex = 0
    private lateinit var topicItem: TopicItem
    private val mainAdapter: MainAdapter by lazy(LazyThreadSafetyMode.NONE) { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topicItem = MainActivity.DataHolder.topicData!!
        val itemDecoration = ItemDecoration(this, R.drawable.divider, getDateString())

        setAdapter()
        binding.apply {
            addTextChangedListener()
            sendMessage()

            messageRecycler.adapter = mainAdapter
            toolbar.apply { setToolBar(this) }
            topicName.text = topicItem.name

            messageRecycler.apply {
                itemAnimator = null
                addItemDecoration(itemDecoration)
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
                mainAdapter.submitList(convertToDelegate(messagesList))
                messageInput.text?.clear()
            }
        }
    }

    private fun openBottomSheet() {
        val bottomSheetDialogFragment = EmojiBottomSheetFragment()
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onEmojiClicked(emoji: String) {
        val position = messagesList[selectedMessageIndex].messageId
        if (position != -1) {
            val lastMessage = messagesList[position]
            val reactionsMap = lastMessage.reactions
            if (!reactionsMap.containsKey(emoji)) {
                reactionsMap[emoji] = reactionsMap.getOrDefault(emoji, 0)
            } else {
                reactionsMap[emoji] = reactionsMap[emoji]!! + 1
            }
            mainAdapter.notifyItemChanged(position)
        }
    }

    private fun getDateString(): String {
        val simpleData = SimpleDateFormat("dd MMM", Locale.ENGLISH)
        val currentDate = Date()
        return simpleData.format(currentDate)

    }

    private fun setToolBar(binding: ToolbarFragmentBinding) {
        binding.toolbar.title = topicItem.name
        binding.backButton.setOnClickListener { finish() }
    }

    private fun setAdapter() {
        val defaultEmojiService = DefaultEmojiService()
        mainAdapter.addDelegate(
            UserMessageDelegate(
                DefaultEmoji.default,
                onLongItemClick = { messageItem ->
                    openBottomSheet()
                    selectedMessageIndex = messageItem.messageId
                },
                onItemClick = { messageItem ->
                    openBottomSheet()
                    selectedMessageIndex = messageItem.messageId
                },
                this
            )
        )

        mainAdapter.addDelegate(
            CompanionMessageDelegate(
                defaultEmojiService,
                onLongItemClick = { messageItem ->
                    openBottomSheet()
                    selectedMessageIndex = messageItem.messageId
                },
                onItemClick = { messageItem ->
                    openBottomSheet()
                    selectedMessageIndex = messageItem.messageId
                },
                this
            )
        )
    }
}
