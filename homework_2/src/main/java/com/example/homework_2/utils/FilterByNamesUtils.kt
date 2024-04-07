package com.example.homework_2.utils

import com.example.homework_2.channels.StreamItem

object FilterByNamesUtils {
    fun filterItemsByName(items: List<StreamItem>, query: String): List<StreamItem> {
        return items.filter { it.name.contains(query, ignoreCase = true) }
    }
}