package com.example.homework_2.data.db.mapper.domain

import com.example.homework_2.data.db.entity.ProfileDbItem
import com.example.homework_2.domain.entity.ProfileItem

fun ProfileDbItem.toDomain(): ProfileItem {
    return ProfileItem(
        id = this.id,
        name = this.name,
        isActive = this.isActive,
        url = this.url,
        email = this.email
    )
}

fun List<ProfileDbItem>.toDomain(): List<ProfileItem> {
    return map { it.toDomain() }
}