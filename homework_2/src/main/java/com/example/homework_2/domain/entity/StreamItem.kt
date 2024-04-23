package com.example.homework_2.domain.entity

data class StreamItem(
    val id: Int,
    val name: String,
    val color: String? = null,
    val description: String,
)