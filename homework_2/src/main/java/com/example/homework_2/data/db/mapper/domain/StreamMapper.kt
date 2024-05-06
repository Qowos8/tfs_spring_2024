package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.StreamDbItem
import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.domain.entity.StreamItem

fun StreamDbItem.toDomain(): StreamItem {
    return StreamItem(
        id = id,
        name = name,
        color = color,
        description = description,
    )
}

fun List<StreamDbItem>.toDomain(): List<StreamItem> {
    return map { it.toDomain() }
}