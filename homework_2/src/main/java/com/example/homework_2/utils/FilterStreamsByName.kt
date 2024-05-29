package com.example.homework_2.utils

import com.example.homework_2.domain.entity.StreamItem

object FilterStreamsByName {
    fun filterItemsByName(items: List<StreamItem>, query: String): List<StreamItem> {
        return items.filter { it.name.contains(query, ignoreCase = true) }
    }
}