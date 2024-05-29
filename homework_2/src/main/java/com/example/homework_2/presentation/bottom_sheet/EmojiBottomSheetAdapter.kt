package com.example.homework_2.presentation.bottom_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.presentation.view.EmojiNCU
import com.example.homework_2.presentation.view.emojiSetNCU
import kotlin.reflect.KFunction2

class EmojiBottomSheetAdapter(private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<EmojiBottomSheetAdapter.ViewHolder>() {

    private val emojiList = emojiSetNCU.take(42)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(emojiList[position])
    }

    override fun getItemCount(): Int {
        return emojiList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val emojiTextView: TextView = itemView.findViewById(android.R.id.text1)

        init {
            emojiTextView.textSize = 22f
        }

        fun bind(emoji: EmojiNCU) {
            emojiTextView.apply {
                text = emoji.getCodeString()
                setOnClickListener {
                    onItemClick(emoji.name)
                }
            }
        }
    }
}
