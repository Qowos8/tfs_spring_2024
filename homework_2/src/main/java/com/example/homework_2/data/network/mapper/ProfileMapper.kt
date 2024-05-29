package com.example.homework_2.data.network.mapper

import com.example.homework_2.data.network.model.profile.ProfileItemApi
import com.example.homework_2.domain.entity.ProfileItem

fun ProfileItemApi.toDomain(): ProfileItem {
    return ProfileItem(
        id = id,
        name = name,
        isActive = isActive,
        url = url,
        email = email
    )
}