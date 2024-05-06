package com.example.homework_2.presentation.people

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.domain.entity.ProfileItem

class PeopleDiffUtil : DiffUtil.ItemCallback<ProfileItem>() {

    override fun areItemsTheSame(oldItem: ProfileItem, newItem: ProfileItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProfileItem, newItem: ProfileItem): Boolean {
        return oldItem == newItem
    }
}