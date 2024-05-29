package com.example.homework_2.utils

import com.example.homework_2.domain.entity.ProfileItem

object FilterUsersByName {
    fun filterItemsByName(items: List<ProfileItem>, query: String): List<ProfileItem> {
        return items.filter { it.name.contains(query, ignoreCase = true) }
    }
}