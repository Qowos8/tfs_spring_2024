package com.example.homework_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework_2.databinding.CompanionMessageComponentBinding
import com.example.homework_2.databinding.UserMessageComponentBinding

class MessageAdapter(
    private val onLongItemClick: (MessageItem) -> Unit,
    private val onItemClick: (MessageItem) -> Unit,
    private val itemList: MutableList<MessageItem>,
    private val currentUserId: Int,
    private val context: Context,
) :
    RecyclerView.Adapter<ViewHolder>() {

    private val VIEW_TYPE_SENT_MESSAGE = 1
    private val VIEW_TYPE_RECEIVED_MESSAGE = 2

    private lateinit var currentEmoji: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_SENT_MESSAGE -> {
                val binding = UserMessageComponentBinding.inflate(inflater, parent, false)
                SentMessageViewHolder(binding)
            }

            VIEW_TYPE_RECEIVED_MESSAGE -> {
                val binding = CompanionMessageComponentBinding.inflate(inflater, parent, false)
                ReceivedMessageViewHolder(binding, onLongItemClick)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = itemList[position]
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


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = itemList[position]
        return if (message.senderId == currentUserId) {
            VIEW_TYPE_SENT_MESSAGE
        } else {
            VIEW_TYPE_RECEIVED_MESSAGE
        }
    }

    inner class ReceivedMessageViewHolder(
        private val viewBinding: CompanionMessageComponentBinding,
        private val onLongItemClick: (MessageItem) -> Unit,
    ) : ViewHolder(viewBinding.root) {
        fun bind(message: MessageItem) {
            viewBinding.apply {
                userNameTextView.text = message.senderName
                messageTextView.text = message.message
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

                viewBinding.flex.removeAllViews()


                message.reactions.forEach { (emoji, count) ->
                    addEmojiView(
                        emoji,
                        count,
                        viewBinding.flex,
                        message
                    )
                }
                setAddButton(addButton, viewBinding.flex, message)

                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }

    fun addEmojiView(emoji: String, count: Int, binding: ViewGroup, message: MessageItem) {
        val emojiView = EmojiCustomView(context)
        val params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        params.setMargins(10, 10, 10, 10)
        emojiView.setBackgroundResource(R.drawable.view_bg)
        emojiView.setPadding(12, 12, 12, 12)
        emojiView.emoji = emoji
        emojiView.reactionCount = count
        emojiView.layoutParams = params

        addView(emojiView, binding)
        emojiView.changeReaction(binding, message)

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
                    removeView(emojiView, binding)
                } else {
                    messageItem.reactions[emoji] = emojiView.reactionCount
                }
            }
            emojiView.tag = isSelected
        }
    }


    private fun <T : View> addView(view: T, binding: ViewGroup) {
        binding.addView(view)
    }

    private fun <T : View> removeView(view: T, binding: ViewGroup) {
        binding.removeView(view)
    }


    fun setAddButton(view: View, binding: ViewGroup, message: MessageItem) {
        if (view.parent != null) {
            (view.parent as ViewGroup).removeView(view)
        }
        if (binding.childCount > 0) {
            binding.addView(view)
            view.visibility = View.VISIBLE
            view.setOnClickListener {
                onItemClick(message)
            }
        }
        else{
            view.visibility = View.GONE
        }
    }

}