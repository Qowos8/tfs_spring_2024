package com.example.homework_2.data.network.mapper

import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.child.model.channels.topic.TopicItemApi

fun TopicItemApi.toDomain(): TopicItem {
    return TopicItem(
        name = name,
        messageCount = messageCount
    )
}