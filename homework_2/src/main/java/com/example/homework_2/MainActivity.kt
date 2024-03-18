package com.example.homework_2

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.example.homework_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentEmojiIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            addEmojiView(this)
        }
        setAvatar()
        setName()
        setMessage()

    }

    private fun addEmojiView(context: Context) {
        val emojiView = EmodjiCustomView(context)
        emojiView.setBackgroundResource(R.drawable.view_bg)
        emojiView.setPadding(12)
        val params = MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        emojiView.layoutParams = params

        if (currentEmojiIndex < emojiSetNCU.size) {
            val emoji = emojiSetNCU[currentEmojiIndex]
            emojiView.emoji = emoji.getCodeString()
            currentEmojiIndex++
            binding.flex.addView(emojiView, binding.flex.childCount)
        }
        else {
            emojiView.reactionCount++
        }
        changeReaction(emojiView)
    }

    private fun changeReaction(emoji: EmodjiCustomView) {
        emoji.setOnClickListener {
            if (!it.isSelected) {
                (it as EmodjiCustomView).reactionCount++
                it.isSelected = !it.isSelected
            } else {
                (it as EmodjiCustomView).reactionCount--
                it.isSelected = !it.isSelected
            }
        }
    }

    private fun setAvatar() {
        binding.apply {
            avatarButton.setOnClickListener {
                avatar.setImageResource(R.drawable.batman)
            }
        }
    }

    private fun setName() {
        binding.apply {
            val nameText = nameInput.text
            nameButton.setOnClickListener {
                name.text = nameText
            }
        }
    }

    private fun setMessage() {
        binding.apply {
            val messageText = messageInput.text
            messageButton.setOnClickListener {
                messageUser.text = messageText
            }
        }
    }
}