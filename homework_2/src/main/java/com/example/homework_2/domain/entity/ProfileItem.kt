package com.example.homework_2.domain.entity

data class ProfileItem(
    val id: Int,
    val name: String,
    val isActive: Boolean,
    val url: String?,
    val email: String?,
)