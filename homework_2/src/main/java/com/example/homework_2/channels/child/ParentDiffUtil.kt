package com.example.homework_2.channels.child

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.channels.AllStreamItem

class ParentDiffUtil : DiffUtil.ItemCallback<AllStreamItem>() {

    override fun areItemsTheSame(oldItem: AllStreamItem, newItem: AllStreamItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AllStreamItem, newItem: AllStreamItem): Boolean {
        return oldItem == newItem
    }
}