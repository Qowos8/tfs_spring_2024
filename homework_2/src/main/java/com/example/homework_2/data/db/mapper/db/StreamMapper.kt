package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.ProfileDbItem
import com.example.homework_2.data.db.entity.StreamDbItem
import com.example.homework_2.data.network.model.channels.stream.StreamItemApi
import com.example.homework_2.data.network.model.profile.ProfileItemApi
import com.example.homework_2.domain.entity.StreamItem

fun StreamItem.toDB(isSub: Int): StreamDbItem {
    return StreamDbItem(
        id = id,
        name = name,
        color = color,
        description = description,
        isSub = isSub
    )
}

fun StreamItemApi.toDB(isSub: Int): StreamDbItem {
    return StreamDbItem(
        id = id,
        name = name,
        color = color,
        description = description,
        isSub = isSub
    )
}

fun List<StreamItem>.toDB(isSub: Int): List<StreamDbItem> {
    return map { it.toDB(isSub) }
}