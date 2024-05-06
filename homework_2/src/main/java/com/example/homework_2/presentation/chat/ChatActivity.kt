package com.example.homework_2.presentation.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.R
import com.example.homework_2.data.network.model.chat.reaction.ReactionItemApi
import com.example.homework_2.databinding.ChatFragmentBinding
import com.example.homework_2.databinding.ToolbarFragmentBinding
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.base.ElmBaseActivity
import com.example.homework_2.presentation.bottom_sheet.BottomSheetClickListener
import com.example.homework_2.presentation.bottom_sheet.EmojiBottomSheetFragment
import com.example.homework_2.presentation.chat.delegate.CompanionMessageDelegate
import com.example.homework_2.presentation.chat.delegate.DefaultEmojiService
import com.example.homework_2.presentation.chat.delegate.UserMessageDelegate
import com.example.homework_2.presentation.chat.di.ChatComponent
import com.example.homework_2.presentation.chat.mvi.ChatEffect
import com.example.homework_2.presentation.chat.mvi.ChatEvent
import com.example.homework_2.presentation.chat.mvi.ChatState
import com.example.homework_2.presentation.chat.mvi.ChatStoreFactory
import com.example.homework_2.presentation.delegate.MainAdapter
import com.example.homework_2.presentation.view.emojiSetNCU
import com.example.homework_2.utils.MessageMapper.convertToDelegate
import com.google.android.material.snackbar.Snackbar
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ChatActivity : ElmBaseActivity<
        ChatEvent,
        ChatEffect,
        ChatState>(), BottomSheetClickListener, OnViewClickListener {
    @Inject
    lateinit var factory: ChatStoreFactory

    private lateinit var binding: ChatFragmentBinding
    private val messagesList = mutableListOf<MessageItem>()
    private val mainAdapter: MainAdapter by lazy(LazyThreadSafetyMode.NONE) { MainAdapter() }

    private lateinit var userDelegate: UserMessageDelegate
    private lateinit var companionDelegate: CompanionMessageDelegate

    private lateinit var streamNameString: String
    private lateinit var topicNameString: String
    private var streamId = 0
    private var selectedMessageIndex = 0
    private var currentListCount = 0
    private var isLoading = false
    private var isFirstScroll = false

    override val store: Store<ChatEvent, ChatEffect, ChatState>
            by elmStoreWithRenderer(elmRenderer = this) {
                factory.provide()
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatComponent().inject(this)
        binding = ChatFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNames()

        store.accept(ChatEvent.Ui.LoadMessages(topicNameString, streamId))
        store.accept(ChatEvent.Ui.RegisterEvent)
        setAdapter()
        binding.apply {
            addTextChangedListener()
            sendMessage()
            toolbar.apply { setToolBar(this) }
            topicName.text = TOPIC_UP_NAME + topicNameString
            messageRecycler.adapter = mainAdapter
            messageRecycler.apply {
                itemAnimator = null
                addItemDecoration(
                    ItemDecoration(
                        this@ChatActivity,
                        R.drawable.divider,
                        getDateString()
                    )
                )
            }
        }
        addScrollListener()
    }

    override fun render(state: ChatState) {
        trackMessages(state)
    }

    private fun trackMessages(state: ChatState) {
        when (state) {
            is ChatState.Error -> {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
                Log.d("sij", state.error)
            }

            ChatState.Init -> {}
            ChatState.Loading -> {}

            is ChatState.NetworkSuccess -> {
                mainAdapter.submitList(convertToDelegate(state.messages))
                if(messagesList != state.messages){
                    messagesList.addAll(state.messages)
                }
                if(state.messages.size == 1){
                    scrollToEndList()
                }
                currentListCount = state.messages.size
                isLoading = false
            }

            is ChatState.CacheSuccess -> {
                mainAdapter.submitList(convertToDelegate(state.messages))
                currentListCount = state.messages.size
                scrollToEndList()
                isLoading = false
                Log.d("chat", state.messages.toString())
            }

            ChatState.CacheEmpty -> {
                store.accept(ChatEvent.Ui.UpdateMessages(topicNameString, streamNameString, streamId, FIRST_LIMIT))
                Log.d("chat", topicNameString + streamNameString + streamId)
            }

            ChatState.CacheLoaded -> {  store.accept(ChatEvent.Ui.LoadMessages(topicNameString, streamId)) }
        }
    }

    private fun ChatFragmentBinding.sendMessage() {
        if (messageButton.isEnabled) {
            messageButton.setOnClickListener {
                store.accept(
                    ChatEvent.Ui.SendMessage(
                        streamNameString,
                        topicNameString,
                        messageInput.text.toString()
                    )
                )
                messageInput.text?.clear()
            }
        }
    }

    override fun onClick(messageId: Int, emojiName: String, count: Int) {
        when (count) {
            1 -> {
                store.accept(ChatEvent.Ui.AddReaction(messageId, emojiName))
            }

            0 -> {
                store.accept(ChatEvent.Ui.DeleteReaction(messageId, emojiName))
            }
        }
    }

    override fun onEmojiClicked(emoji: String) {
        store.accept(ChatEvent.Ui.AddReaction(selectedMessageIndex, emoji))
        val message = messagesList.find { it.id == selectedMessageIndex }

        if (message?.reactions != null) {
            val newReaction = ReactionItemApi(
                emojiName = emoji,
                emojiCode = emojiSetNCU.find { it.name == emoji }?.getCodeString(),
                reactionType = REACTION_TYPE,
                userId = message.userId
            )
            message.reactions.add(newReaction)
            messagesList.replaceAll { if (it.id == selectedMessageIndex) message else it }
            mainAdapter.submitList(convertToDelegate(messagesList))
            mainAdapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter() {
        val defaultEmojiService = DefaultEmojiService(this)
        userDelegate = UserMessageDelegate(
            defaultEmojiService,
            onLongItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id
            },
            onItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id
            },
            this
        )
        companionDelegate = CompanionMessageDelegate(
            defaultEmojiService,
            onLongItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id
            },
            onItemClick = { messageItem ->
                openBottomSheet()
                selectedMessageIndex = messageItem.id
            },
            this
        )
        mainAdapter.addDelegate(userDelegate)
        mainAdapter.addDelegate(companionDelegate)

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

    private fun getNames() {
        streamNameString = intent.getStringExtra(STREAM_NAME).toString()
        topicNameString = intent.getStringExtra(TOPIC_NAME).toString()
        streamId = intent.getIntExtra(STREAM_ID, 0)
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

    private fun openBottomSheet() {
        val bottomSheetDialogFragment = EmojiBottomSheetFragment()
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    private fun scrollToEndList(){
        if (!isFirstScroll){
            binding.messageRecycler.postDelayed({
                binding.messageRecycler.scrollToPosition((mainAdapter.currentList.size) - 1)
            }, 300)
            isFirstScroll = true
        }
    }

    private fun addScrollListener(){
        binding.messageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleElement = layoutManager.findFirstVisibleItemPosition()
                if(lastVisibleElement == 5 && currentListCount!=0 && !isLoading){
                    isLoading = true
                    store.accept(ChatEvent.Ui.UpdateMessages(
                        nextCount = currentListCount + 20,
                        streamName = streamNameString,
                        topicName = topicNameString,
                        streamId = streamId)
                    )
                    Log.d("count", (currentListCount + 20).toString())
                }
            }
        })
    }

    companion object{
        private const val STREAM_NAME = "streamName"
        private const val TOPIC_NAME = "topicName"
        private const val STREAM_ID = "streamId"
        private const val REACTION_TYPE = "reaction"
        private const val TOPIC_UP_NAME = "Topic: #"
        private const val FIRST_LIMIT = 20
    }
}
