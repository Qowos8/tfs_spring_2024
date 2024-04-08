package com.example.homework_2.chat.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.homework_2.R
import com.example.homework_2.chat.EmojiService
import com.example.homework_2.chat.MessageItem
import com.example.homework_2.utils.ViewUtils
import com.example.homework_2.view.EmojiCustomView

class DefaultEmojiService : EmojiService {

    override fun addEmojiView(
        context: Context,
        emoji: String,
        count: Int,
        binding: ViewGroup,
        message: MessageItem,
    ) {
        val inflater = LayoutInflater.from(context)
        val emojiView = inflater.inflate(R.layout.emoji_view, binding, false) as EmojiCustomView

        emojiView.emoji = emoji
        emojiView.reactionCount = count

        binding.addView(emojiView)
        binding.isVisible = true
        changeReaction(binding, emojiView, message)
        message.reactionsView.add(emojiView)
    }

    override fun changeReaction(
        binding: ViewGroup,
        emojiView: EmojiCustomView,
        messageItem: MessageItem,
    ) {
        emojiView.setOnClickListener { view ->
            val currentEmojiView = view as EmojiCustomView
            val isSelected = !currentEmojiView.isSelected
            val emoji = currentEmojiView.emoji
            emojiView.isSelected = isSelected

            if (isSelected) {
                emojiView.reactionCount++
                messageItem.reactions[emoji] = currentEmojiView.reactionCount
            } else {
                currentEmojiView.reactionCount--
                if (currentEmojiView.reactionCount == 0) {
                    messageItem.reactions.remove(emoji)
                    ViewUtils.removeView(view, binding)
                    if (messageItem.reactions.isEmpty()) {
                        binding.removeAllViews()
                    }
                } else {
                    messageItem.reactions[emoji] = currentEmojiView.reactionCount
                }
            }
        }
    }

    override fun setAddButton(
        view: ImageView,
        binding: ViewGroup,
        message: MessageItem,
        onItemClick: (MessageItem) -> Unit,
    ) {
        binding.addView(view)
        view.setOnClickListener {
            onItemClick(message)
        }
    }
}