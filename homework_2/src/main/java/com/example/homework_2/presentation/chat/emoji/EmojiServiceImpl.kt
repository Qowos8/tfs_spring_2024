package com.example.homework_2.presentation.chat.emoji

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.homework_2.R
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.utils.ViewUtils
import com.example.homework_2.presentation.view.EmojiCustomView

class EmojiServiceImpl(private val onEmojiClickListener: OnEmojiClickListener) : EmojiService {

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
        if (message.reactions != null){
            for(reaction in message.reactions){
                if(reaction?.user?.id == USER_ID || reaction?.user?.id == null){
                    emojiView.isSelected = true
                }
            }
        }
        val messageId = message.id

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
        message_Id: Int,
        emoji_Name: String,
    ) {
        var clicked: EmojiClickState

        emojiView.setOnClickListener { view ->
            val currentEmojiView = view as EmojiCustomView
            val isSelected = !currentEmojiView.isSelected
            emojiView.isSelected = isSelected

            if (isSelected) {
                emojiView.reactionCount++
                clicked = EmojiClickState.Clicked
            } else {
                currentEmojiView.reactionCount--
                clicked = EmojiClickState.UnClicked
                if (currentEmojiView.reactionCount == 0) {
                    ViewUtils.removeView(view, binding)
                    if (messageItem.reactions?.isEmpty() == true) {
                        binding.removeAllViews()
                    }
                }
            }
            val clickListener =
                OnEmojiClickListener { messageId, emojiName, count ->
                    onEmojiClickListener.onClick(messageId, emojiName, clicked)
                }
            clickListener.onClick(message_Id, emoji_Name, clicked)
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
        private const val USER_ID = 709571L
    }
}