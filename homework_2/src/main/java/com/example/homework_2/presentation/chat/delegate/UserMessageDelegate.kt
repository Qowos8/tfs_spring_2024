package com.example.homework_2.presentation.chat.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.presentation.chat.MessageItem
import com.example.homework_2.databinding.UserMessageComponentBinding
import com.example.homework_2.presentation.delegate.AdapterDelegate
import com.example.homework_2.presentation.delegate.DelegateItem
import com.example.homework_2.utils.HtmlToString
import com.example.homework_2.presentation.view.emojiSetNCU

class UserMessageDelegate(
    private val default: DefaultEmojiService,
    private val onLongItemClick: (MessageItem) -> Unit,
    private val onItemClick: (MessageItem) -> Unit,
    private val context: Context,
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserMessageComponentBinding.inflate(inflater, parent, false)
        return SentMessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
    ) {
        (holder as SentMessageViewHolder).bind(item.content() as MessageItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean {
        if (item is MessageDelegateItem) {
            val message = item.value
            return message.userFullName == USERNAME
        }
        return false
    }

    inner class SentMessageViewHolder(
        private val viewBinding: UserMessageComponentBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(message: MessageItem) {
            viewBinding.apply {
                messageTextView.text =
                    message.content?.let { HtmlToString.convertToString(it).trim() }

                flex.removeAllViews()
                if (message.reactions?.isNotEmpty() == true) {
                    val reactionCounts = message.reactions
                        .filterNotNull()
                        .groupBy { it.emojiName }
                        .mapValues { it.value.size }
                    reactionCounts.forEach { (emojiName, count) ->
                        val emojiItem = emojiSetNCU.find { it.name == emojiName }?.getCodeString()
                        if (emojiItem != null && emojiName != null) {
                            default.addEmojiView(context, emojiItem, emojiName, count, flex, message)
                        }
                    }
                }
                default.setAddButton(addButton, flex, message, onItemClick)

                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }

    private companion object {
        private const val USERNAME = "Namsr Nadbitov"
    }
}