package com.example.homework_2.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
import com.example.homework_2.network.ReactionResponse
import com.example.homework_2.utils.MessageMapper.convertToDelegate
import com.example.homework_2.view.emojiSetNCU
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity(), BottomSheetClickListener, OnViewClickListener {
    private lateinit var binding: ChatFragmentBinding
    private val viewModel: ChatViewModel by viewModels()
    private val messagesList = mutableListOf<MessageItem>()
    private var selectedMessageIndex = 0
    private lateinit var topicItem: TopicItem
    private val mainAdapter: MainAdapter by lazy(LazyThreadSafetyMode.NONE) { MainAdapter() }
    private lateinit var userDelegate: UserMessageDelegate
    private lateinit var companionDelegate: CompanionMessageDelegate

    private lateinit var streamNameString: String
    private lateinit var topicNameString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        streamNameString = intent.getStringExtra("streamName").toString()
        topicNameString = intent.getStringExtra("topicName").toString()
        topicItem = MainActivity.DataHolder.topicData!!
        val itemDecoration = ItemDecoration(this, R.drawable.divider, getDateString())
        viewModel.getMessages(streamNameString, topicNameString)
        setAdapter()
        binding.apply {
            addTextChangedListener()
            sendMessage()
            messageRecycler.adapter = mainAdapter
            toolbar.apply { setToolBar(this) }
            topicName.text = "Topic: #$topicNameString"
            messageRecycler.apply {
                itemAnimator = null
                addItemDecoration(itemDecoration)
            }
        }
        trackMessages()
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
                viewModel.sendMessage(
                    streamNameString,
                    topicNameString,
                    messageInput.text.toString()
                )
                messageInput.text?.clear()
                messageRecycler.post {
                    messageRecycler.smoothScrollToPosition((mainAdapter.currentList.size + 1) - 2)
                }
            }
        }
    }

    private fun openBottomSheet() {
        val bottomSheetDialogFragment = EmojiBottomSheetFragment()
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    override fun onEmojiClicked(emoji: String) {
        viewModel.sendReaction(emoji, selectedMessageIndex)
        val message = messagesList.find { it.id.toInt() == selectedMessageIndex }

        if (message?.reactions != null) {
            val newReaction = ReactionResponse(
                emojiName = emoji,
                emojiCode = emojiSetNCU.find { it.name == emoji }?.getCodeString(),
                reactionType = "reaction",
                userId = message.userId?.toInt()
            )
            message.reactions.add(newReaction)
            messagesList.replaceAll { if (it.id.toInt() == selectedMessageIndex) message else it }
            mainAdapter.submitList(convertToDelegate(messagesList))
            mainAdapter.notifyDataSetChanged()
        }
    }


    private fun getDateString(): String {
        val simpleData = SimpleDateFormat("dd MMM", Locale.ENGLISH)
        val currentDate = Date()
        return simpleData.format(currentDate)

    }

    private fun setToolBar(binding: ToolbarFragmentBinding) {
        binding.toolbar.title = streamNameString
        binding.backButton.setOnClickListener { finish() }
    }

    private fun setAdapter() {
        val defaultEmojiService = DefaultEmojiService(this)
        userDelegate = UserMessageDelegate(
            defaultEmojiService,
            onLongItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id.toInt()
            },
            onItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id.toInt()
            },
            this
        )
        companionDelegate = CompanionMessageDelegate(
            defaultEmojiService,
            onLongItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id.toInt()
            },
            onItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id.toInt()
            },
            this
        )
        mainAdapter.addDelegate(userDelegate)
        mainAdapter.addDelegate(companionDelegate)

    }

    private fun trackMessages() {
        lifecycleScope.launch {
            viewModel.messagesState.collect { state ->
                when (state) {
                    is ChatState.Error -> {
                        Log.d("chat", state.error)
                    }

                    ChatState.Init -> {}
                    ChatState.Loading -> {}

                    is ChatState.Success -> {
                        mainAdapter.submitList(convertToDelegate(state.messages))
                        messagesList.addAll(state.messages)
                        binding.messageRecycler.scrollToPosition(state.messages.size - 1)
                    }
                }
            }
        }
    }

    override fun onClick(messageId: Int, emojiName: String, count: Int) {
        when(count){
            1 -> {
                viewModel.sendReaction(emojiName, messageId)
            }
            0 -> {
                viewModel.deleteReaction(emojiName, messageId)
            }
        }
    }
}
