package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.ProfileDbItem
import com.example.homework_2.data.network.model.profile.ProfileItemApi
import com.example.homework_2.domain.entity.ProfileItem

fun ProfileItemApi.toDB(): ProfileDbItem {
    return ProfileDbItem(
        id = id,
        name = name,
        isActive = isActive,
        url = url,
        email = email
    )
}

fun ProfileItem.toDB(): ProfileDbItem {
    return ProfileDbItem(
        id = id,
        name = name,
        isActive = isActive,
        url = url,
        email = email
    )
}

fun List<ProfileItemApi>.toDB(): List<ProfileDbItem> {
    return map { it.toDB() }
}