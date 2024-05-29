package com.example.homework_2.presentation.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.R
import com.example.homework_2.databinding.ChatActivityBinding
import com.example.homework_2.databinding.ToolbarFragmentBinding
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.entity.ReactionItem
import com.example.homework_2.presentation.base.ElmBaseActivity
import com.example.homework_2.presentation.bottom_sheet.BottomSheetClickListener
import com.example.homework_2.presentation.bottom_sheet.EmojiBottomSheetFragment
import com.example.homework_2.presentation.chat.delegate.CompanionMessageDelegate
import com.example.homework_2.presentation.chat.delegate.UserMessageDelegate
import com.example.homework_2.presentation.chat.di.ChatComponent
import com.example.homework_2.presentation.chat.emoji.EmojiClickState
import com.example.homework_2.presentation.chat.emoji.EmojiServiceImpl
import com.example.homework_2.presentation.chat.mvi.ChatEffect
import com.example.homework_2.presentation.chat.mvi.ChatEvent
import com.example.homework_2.presentation.chat.mvi.ChatHolderState
import com.example.homework_2.presentation.chat.mvi.ChatStoreFactory
import com.example.homework_2.presentation.chat.mvi.LoadingState
import com.example.homework_2.presentation.delegate.MainAdapter
import com.example.homework_2.presentation.view.emojiSetNCU
import com.example.homework_2.utils.MessageMapper.convertFromDelegate
import com.example.homework_2.utils.MessageMapper.convertListToDelegate
import com.example.homework_2.utils.MessageMapper.convertSingleToDelegate
import com.google.android.material.snackbar.Snackbar
import vivid.money.elmslie.android.renderer.elmStoreWithRenderer
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class ChatActivity : ElmBaseActivity<
        ChatEvent,
        ChatEffect,
        ChatHolderState>(), BottomSheetClickListener {
    @Inject
    lateinit var factory: ChatStoreFactory

    private lateinit var binding: ChatActivityBinding

    private val mainAdapter: MainAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainAdapter().apply {
            val emojiServiceImpl =
                EmojiServiceImpl { messageId, emojiName, clickState ->
                    when (clickState) {
                        EmojiClickState.Clicked -> store.accept(
                            ChatEvent.Ui.AddReaction(messageId, emojiName)
                        )

                        EmojiClickState.UnClicked -> store.accept(
                            ChatEvent.Ui.DeleteReaction(messageId, emojiName)
                        )
                    }
                }

            addDelegate(
                UserMessageDelegate(
                    emojiService = emojiServiceImpl,
                    onLongItemClick = { messageItem -> openBottomSheet(messageItem.id) },
                    onItemClick = { messageItem -> openBottomSheet(messageItem.id) },
                )
            )
            addDelegate(
                CompanionMessageDelegate(
                    emojiService = emojiServiceImpl,
                    onLongItemClick = { messageItem -> openBottomSheet(messageItem.id) },
                    onItemClick = { messageItem -> openBottomSheet(messageItem.id) },
                )
            )
        }
    }

    private val currentListCount
        get() = store.states.value.messages.size

    private var isLoading = false

    override val store: Store<ChatEvent, ChatEffect, ChatHolderState> by
        elmStoreWithRenderer(elmRenderer = this) {
        factory.provide()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatComponent().inject(this)

        binding = ChatActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topicName = intent.getStringExtra(TOPIC_NAME).toString()
        val streamId = intent.getIntExtra(STREAM_ID, 0)
        val streamName = intent.getStringExtra(STREAM_NAME).toString()

        initStore(
            topicName = topicName,
            streamId = streamId,
            streamName = streamName,
        )

        binding.apply {
            setupToolbarTitles(topicName = topicName, streamName = streamName)
            addTextChangedListener()
            sendMessage()
            setupRecycler()
        }

        addScrollListener()
        initMessages()
    }

    private fun ChatActivityBinding.sendMessage() {
        if (messageButton.isEnabled) {
            messageButton.setOnClickListener {
                store.accept(
                    ChatEvent.Ui.SendMessage(messageInput.text.toString())
                )
                messageInput.text?.clear()
            }
        }
    }

    private fun ChatActivityBinding.setupRecycler() {
        messageRecycler.apply {
            adapter = mainAdapter
            addItemDecoration(
                ItemDecoration(
                    this@ChatActivity,
                    R.drawable.divider,
                )
            )
        }
        messageRecycler.layoutManager = LinearLayoutManager(
            this@ChatActivity,
            RecyclerView.VERTICAL,
            false
        ).apply { stackFromEnd = true }
    }

    override fun render(state: ChatHolderState) {
        when (state.loadingState) {
            LoadingState.Loading -> Unit
            LoadingState.Init -> Unit
            LoadingState.CacheLoaded -> {
                store.accept(ChatEvent.Ui.LoadMessages)
            }

            LoadingState.NetworkSuccess -> {
                mainAdapter.submitList(convertListToDelegate(state.messages))
                isLoading = false
            }

            LoadingState.CacheSuccess -> {
                mainAdapter.submitList(convertListToDelegate(state.messages))
                isLoading = false
            }

            LoadingState.CacheEmpty -> {
                store.accept(ChatEvent.Ui.UpdateMessages(FIRST_LIMIT))
            }

            LoadingState.Error -> {
                Snackbar.make(binding.root, state.loadingState.name, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onEmojiClicked(emoji: String, selectedMessage: Int) {
        if (!findSelectedEmoji(selectedMessage, emoji)) {
            store.accept(ChatEvent.Ui.AddReaction(selectedMessage, emoji))
            val index = mainAdapter.currentList.indexOfFirst { it.id() == selectedMessage }
            if (index != -1) {
                val currentMessage = mainAdapter.currentList[index]?.let { convertFromDelegate(it) }
                if (currentMessage != null) {
                    val newReaction = ReactionItem(
                        emojiName = emoji,
                        emojiCode = (emojiSetNCU.find { it.name == emoji }!!.code.toString(16)),
                        reactionType = REACTION_TYPE,
                        userId = currentMessage.userId
                    )
                    val updatedReactions = currentMessage.reactions?.toMutableList()?.apply {
                        add(newReaction)
                    }
                    val updatedMessage = currentMessage.copy(reactions = updatedReactions)
                    val updatedList = mainAdapter.currentList.toMutableList().apply {
                        set(index, convertSingleToDelegate(updatedMessage))
                    }
                    mainAdapter.submitList(updatedList)
                }
            }
        }
    }

    private fun ChatActivityBinding.addTextChangedListener() {
        messageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

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

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun initStore(
        topicName: String,
        streamId: Int,
        streamName: String,
    ) {
        store.accept(
            ChatEvent.Ui.Init(
                topicName = topicName,
                streamId = streamId,
                streamName = streamName
            )
        )
    }

    private fun initMessages() {
        store.accept(ChatEvent.Ui.LoadMessages)
        store.accept(ChatEvent.Ui.RegisterEvent)
    }

    private fun ChatActivityBinding.setupToolbarTitles(streamName: String, topicName: String) {
        toolbarChat.setupToolbar(streamName)
        this.topicName.text = "$TOPIC_UP_NAME $topicName"
    }

    private fun ToolbarFragmentBinding.setupToolbar(streamName: String) {
        toolbar.title = "#$streamName"
        backButton.setOnClickListener { finish() }
    }

    private fun openBottomSheet(currentMessageId: Int) {
        val bottomSheetDialogFragment = EmojiBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putInt(SELECTED_MESSAGE, currentMessageId)
            }
        }
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }

    private fun findSelectedEmoji(id: Int, emoji: String): Boolean {
        val selectedMessageItem =
            mainAdapter.currentList.find { it.id() == id }?.content() as MessageItem
        val selectedEmoji = selectedMessageItem.reactions?.find { it?.emojiName == emoji }
        val emojiUserId = selectedEmoji?.user?.id
        return ((emojiUserId == USER_ID || emojiUserId == null) && selectedEmoji != null)
    }

    private fun addScrollListener() {
        binding.messageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleElement = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (lastVisibleElement == LAST_VISIBLE && currentListCount != 0 && !(isLoading)) {
                    isLoading = true
                    store.accept(
                        ChatEvent.Ui.UpdateMessages(nextCount = currentListCount + 20)
                    )
                    store.accept(ChatEvent.Ui.LoadingPage)
                }
            }
        })
    }

    companion object {
        private const val STREAM_NAME = "streamName"
        private const val TOPIC_NAME = "topicName"
        private const val STREAM_ID = "streamId"
        private const val REACTION_TYPE = "reaction"
        private const val TOPIC_UP_NAME = "Topic: #"
        private const val SELECTED_MESSAGE = "selected"
        private const val FIRST_LIMIT = 20
        private const val LAST_VISIBLE = 5
        private const val USER_ID = 709571L
    }
}