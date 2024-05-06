package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.TopicDbItem
import com.example.homework_2.domain.entity.TopicItem

fun TopicDbItem.toDomain(): TopicItem {
    return TopicItem(
        name = name,
        messageCount = messageCount
    )
}
fun List<TopicDbItem>.toDomain(): List<TopicItem> {
    return map { it.toDomain() }
}