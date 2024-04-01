package com.example.homework_2.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework_2.DiffUtilAdapterItemCallback
import com.example.homework_2.R
import com.example.homework_2.databinding.CompanionMessageComponentBinding
import com.example.homework_2.databinding.UserMessageComponentBinding
import com.example.homework_2.view.EmojiCustomView

class MessageAdapter(
    private val onLongItemClick: (MessageItem) -> Unit,
    private val onItemClick: (MessageItem) -> Unit,
    private val context: Context,
) :
    ListAdapter<MessageItem, ViewHolder>(DiffUtilAdapterItemCallback()) {

    private val VIEW_TYPE_SENT_MESSAGE = 1
    private val VIEW_TYPE_RECEIVED_MESSAGE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_SENT_MESSAGE -> {
                val binding = UserMessageComponentBinding.inflate(inflater, parent, false)
                SentMessageViewHolder(binding)
            }

            VIEW_TYPE_RECEIVED_MESSAGE -> {
                val binding = CompanionMessageComponentBinding.inflate(inflater, parent, false)
                ReceivedMessageViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = getItem(position)

        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams

        when (holder.itemViewType) {
            VIEW_TYPE_SENT_MESSAGE -> {
                (holder as SentMessageViewHolder).bind(message)
            }

            VIEW_TYPE_RECEIVED_MESSAGE -> {
                (holder as ReceivedMessageViewHolder).bind(message)
            }
        }
        holder.itemView.layoutParams = layoutParams
    }


    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int): Int {
        val message = currentList[position]
        return if (message.senderId == VIEW_TYPE_SENT_MESSAGE) {
            VIEW_TYPE_SENT_MESSAGE
        } else {
            VIEW_TYPE_RECEIVED_MESSAGE
        }
    }

    inner class ReceivedMessageViewHolder(
        private val viewBinding: CompanionMessageComponentBinding,
    ) : ViewHolder(viewBinding.root) {

        fun bind(message: MessageItem) {
            viewBinding.apply {
                userNameTextView.text = message.senderName
                messageTextView.text = message.message

                flex.removeAllViews()

                message.reactions.forEach { (emoji, count) ->
                    addEmojiView(emoji, count, flex, message)
                    setAddButton(addButton, flex, message)
                }

                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }

    inner class SentMessageViewHolder(
        private val viewBinding: UserMessageComponentBinding,
    ) : ViewHolder(viewBinding.root) {
        fun bind(message: MessageItem) {
            viewBinding.apply {
                messageTextView.text = message.message

                flex.removeAllViews()

                message.reactions.forEach { (emoji, count) ->
                    addEmojiView(emoji, count, flex, message)
                    setAddButton(addButton, flex, message)
                }

                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }

    fun addEmojiView(emoji: String, count: Int, binding: ViewGroup, message: MessageItem) {
        val inflater = LayoutInflater.from(context)
        val emojiView = inflater.inflate(R.layout.emoji_view, binding, false) as EmojiCustomView

        emojiView.emoji = emoji
        emojiView.reactionCount = count

        binding.addView(emojiView)

        emojiView.changeReaction(binding, message)
        message.reactionsView.add(emojiView)
    }

    private fun EmojiCustomView.changeReaction(binding: ViewGroup, messageItem: MessageItem) {
        setOnClickListener { view ->
            val emojiView = view as EmojiCustomView
            val isSelected = !emojiView.isSelected
            emojiView.isSelected = isSelected

            if (isSelected) {
                emojiView.reactionCount++
                messageItem.reactions[emoji] = emojiView.reactionCount
            } else {
                emojiView.reactionCount--
                if (emojiView.reactionCount == 0) {
                    messageItem.reactions.remove(emoji)
                    removeView(view, binding)
                    if(messageItem.reactions.isEmpty()){
                        binding.removeAllViews()
                    }
                } else {
                    messageItem.reactions[emoji] = emojiView.reactionCount
                }
            }
        }
    }

    private fun <T : View> removeView(view: T, binding: ViewGroup) {
        binding.removeView(view)
    }

    fun setAddButton(view: View, binding: ViewGroup, message: MessageItem) {
        (view.parent as? ViewGroup)?.removeView(view)
        binding.addView(view)

        if (binding.childCount > 0) {
            view.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    onItemClick(message)
                }
            }
        } else {
            view.visibility = View.GONE
        }
    }
}