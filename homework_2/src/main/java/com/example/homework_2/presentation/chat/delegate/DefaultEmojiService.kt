package com.example.homework_2.presentation.chat.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.homework_2.R
import com.example.homework_2.data.network.model.chat.message.MessageItemApi
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.chat.EmojiService
import com.example.homework_2.presentation.chat.OnViewClickListener
import com.example.homework_2.utils.ViewUtils
import com.example.homework_2.presentation.view.EmojiCustomView

class DefaultEmojiService(private val onViewClickListener: OnViewClickListener) : EmojiService {

    private var clicked = 0

    override fun addEmojiView(
        context: Context,
        emoji: String,
        emojiName: String,
        count: Int,
        binding: ViewGroup,
        message: MessageItem,
    ) {
        val inflater = LayoutInflater.from(context)
        val emojiView = inflater.inflate(R.layout.emoji_view, binding, false) as EmojiCustomView
        if((message.userId?.toInt() ?: USERID) == USERID){
            emojiView.isSelected = true
        }
        val messageId = message.id.toInt()

        emojiView.emoji = emoji
        emojiView.reactionCount = count

        binding.addView(emojiView)
        binding.isVisible = true
        changeReaction(binding, emojiView, message, messageId, emojiName)
    }

    override fun changeReaction(
        binding: ViewGroup,
        emojiView: EmojiCustomView,
        messageItem: MessageItem,
        messageId: Int,
        emojiName: String,
    ) {
        emojiView.setOnClickListener { view ->
            val currentEmojiView = view as EmojiCustomView
            val isSelected = !currentEmojiView.isSelected
            val emoji = currentEmojiView.emoji
            emojiView.isSelected = isSelected

            if (isSelected) {
                emojiView.reactionCount++
                clicked = 1
            } else {
                currentEmojiView.reactionCount--
                clicked = -1
                if (currentEmojiView.reactionCount == 0) {
                    ViewUtils.removeView(view, binding)
                    if (messageItem.reactions?.isEmpty() == true) {
                        binding.removeAllViews()
                    }
                } else {
                    clicked = 0
                }
            }
            val clickListener =
                OnViewClickListener { messageId, emojiName, count ->
                    onViewClickListener.onClick(messageId, emojiName, clicked)
                }
            clickListener.onClick(messageId, emojiName, clicked)
        }
    }


    override fun setAddButton(
        view: ImageView,
        binding: ViewGroup,
        message: MessageItem,
        onItemClick: (MessageItem) -> Unit,
    ) {
        binding.removeView(view)
        binding.addView(view)
        view.setOnClickListener {
            onItemClick(message)
        }
    }
    private companion object{
        private const val USERID = 709571
    }
}