package com.example.homework_2.bottom_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.BottomSheetClickListener

class EmojiBottomSheetAdapter(private val emojiList: List<String>) :
    RecyclerView.Adapter<EmojiBottomSheetAdapter.ViewHolder>() {

    private lateinit var emojiClickListener: BottomSheetClickListener

    fun setEmojiClickListener(listener: BottomSheetClickListener) {
        this.emojiClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)

        return ViewHolder(view, emojiClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val emoji = emojiList[position]
        holder.bind(emoji)
    }

    override fun getItemCount(): Int {
        return emojiList.size
    }

    inner class ViewHolder(itemView: View, private val clickListener: BottomSheetClickListener) : RecyclerView.ViewHolder(itemView) {
        private val emojiTextView: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(emoji: String) {
            emojiTextView.apply {
                text = emoji
                textSize = 22f
                setOnClickListener {
                    clickListener.onEmojiClicked(emoji)
                }
            }
        }
    }
}
