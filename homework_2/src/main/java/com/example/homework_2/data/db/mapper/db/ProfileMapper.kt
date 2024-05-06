package com.example.homework_2.data.db.mapper.db

import com.example.homework_2.data.db.entity.ProfileDbItem
import com.example.homework_2.data.network.model.profile.ProfileItemApi
import com.example.homework_2.domain.entity.ProfileItem

fun ProfileItemApi.toDB(): ProfileDbItem {
    return ProfileDbItem(
        id = this.id,
        name = this.name,
        isActive = this.isActive,
        url = this.url,
        email = this.email
    )
}

fun ProfileItem.toDB(): ProfileDbItem {
    return ProfileDbItem(
        id = this.id,
        name = this.name,
        isActive = this.isActive,
        url = this.url,
        email = this.email
    )
}

fun List<ProfileItemApi>.toDB(): List<ProfileDbItem> {
    return map { it.toDB() }
}