package com.example.homework_2.presentation.chat.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.homework_2.presentation.chat.MessageItem
import com.example.homework_2.databinding.CompanionMessageComponentBinding
import com.example.homework_2.presentation.delegate.AdapterDelegate
import com.example.homework_2.presentation.delegate.DelegateItem
import com.example.homework_2.utils.HtmlToString
import com.example.homework_2.presentation.view.emojiSetNCU

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

    override fun isOfViewType(item: DelegateItem): Boolean {
        if (item is MessageDelegateItem) {
            val message = item.value
            return message.userFullName != USERNAME
        }
        return true
    }

    inner class ReceivedMessageVieHolder(
        private val viewBinding: CompanionMessageComponentBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(message: MessageItem) {
            viewBinding.apply {
                userNameTextView.text = message.userFullName
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

                Glide.with(avatar)
                    .load(message.avatarUrl)
                    .override(Target.SIZE_ORIGINAL)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(CORNER_RADIUS)))
                    .into(avatar)
                root.setOnLongClickListener {
                    onLongItemClick(message)
                    true
                }
            }
        }
    }

    private companion object {
        private const val USERNAME = "Namsr Nadbitov"
        private const val CORNER_RADIUS = 16
    }
}