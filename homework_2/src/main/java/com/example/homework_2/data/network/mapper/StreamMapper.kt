package com.example.homework_2.data.network.mapper

import com.example.homework_2.data.network.model.channels.stream.StreamItemApi
import com.example.homework_2.domain.entity.StreamItem

fun StreamItemApi.toDomain(): StreamItem {
    return StreamItem(
        id = id,
        name = name,
        color = color,
        description = description,
    )
}