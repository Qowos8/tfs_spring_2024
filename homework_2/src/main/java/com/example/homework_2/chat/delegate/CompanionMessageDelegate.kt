package com.example.homework_2.chat.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.chat.MessageItem
import com.example.homework_2.databinding.CompanionMessageComponentBinding
import com.example.homework_2.delegate.AdapterDelegate
import com.example.homework_2.delegate.DelegateItem

class CompanionMessageDelegate(
    private val default: DefaultEmojiService,
    private val onLongItemClick: (MessageItem) -> Unit,
    private val onItemClick: (MessageItem) -> Unit,
    private val context: Context,
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CompanionMessageComponentBinding.inflate(inflater, parent, false)
        return ReceivedMessageVieHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
    ) {
        (holder as ReceivedMessageVieHolder).bind(item.content() as MessageItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is MessageDelegateItem

    inner class ReceivedMessageVieHolder(
        private val viewBinding: CompanionMessageComponentBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(message: MessageItem) {
            viewBinding.apply {
                userNameTextView.text = message.senderName
                messageTextView.text = message.message

                flex.removeAllViews()

                message.reactions.forEach { (emoji, count) ->
                    default.addEmojiView(context, emoji, count, flex, message)
                    default.setAddButton(addButton, flex, message, onItemClick)
                }

                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }
}