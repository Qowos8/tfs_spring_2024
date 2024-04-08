package com.example.homework_2.chat.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.chat.MessageItem
import com.example.homework_2.databinding.UserMessageComponentBinding
import com.example.homework_2.delegate.AdapterDelegate
import com.example.homework_2.delegate.DelegateItem

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

    override fun isOfViewType(item: DelegateItem): Boolean = item is MessageDelegateItem

    inner class SentMessageViewHolder(
        private val viewBinding: UserMessageComponentBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(message: MessageItem) {
            viewBinding.apply {
                messageTextView.text = message.message

                flex.removeAllViews()

                message.reactions.forEach { (emoji, count) ->
                    default.addEmojiView(context, emoji, count, flex, message)
                }
                default.setAddButton(addButton, flex, message, onItemClick)

                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }
}