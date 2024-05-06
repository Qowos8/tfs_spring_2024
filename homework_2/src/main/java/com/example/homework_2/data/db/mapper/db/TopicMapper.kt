package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.TopicDbItem
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.child.model.channels.topic.TopicItemApi

fun TopicItem.toDB(streamId: Int): TopicDbItem {
    return TopicDbItem(
        name = name,
        messageCount = messageCount,
        streamId = streamId
    )
}

fun TopicItemApi.toDB(streamId: Int): TopicDbItem {
    return TopicDbItem(
        name = name,
        messageCount = messageCount,
        streamId = streamId
    )
}