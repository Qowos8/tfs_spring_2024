package com.example.homework_2

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.databinding.EmojiFieldBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fieldBinding: EmojiFieldBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        fieldBinding = EmojiFieldBinding.bind(binding.root)
        binding.addButton.setOnClickListener {
            addEmojiView(this)
        }
//        binding.flex.setOnClickListener {
//            (it as EmodjiCustomView).reactionCount++
//            it.isSelected = !it.isSelected
//        }

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
        binding.flex.addView(emojiView, binding.flex.childCount)
    }
}