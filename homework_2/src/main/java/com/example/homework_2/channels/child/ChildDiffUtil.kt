package com.example.homework_2.channels.child

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.channels.TopicItem

class ChildDiffUtil : DiffUtil.ItemCallback<TopicItem>() {

    override fun areItemsTheSame(oldItem: TopicItem, newItem: TopicItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TopicItem, newItem: TopicItem): Boolean {
        return oldItem == newItem
    }
}