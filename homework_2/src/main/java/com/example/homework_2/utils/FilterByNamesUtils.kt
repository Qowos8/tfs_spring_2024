package com.example.homework_2.utils

import com.example.homework_2.presentation.channels.AllStreamItem

object FilterByNamesUtils {
    fun filterItemsByName(items: List<AllStreamItem>, query: String): List<AllStreamItem> {
        return items.filter { it.name.contains(query, ignoreCase = true) }
    }
}