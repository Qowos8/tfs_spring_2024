package com.example.homework_2.presentation.channels.parent

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.domain.entity.StreamItem

class ParentDiffUtil : DiffUtil.ItemCallback<StreamItem>() {

    override fun areItemsTheSame(oldItem: StreamItem, newItem: StreamItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StreamItem, newItem: StreamItem): Boolean {
        return oldItem == newItem
    }
}