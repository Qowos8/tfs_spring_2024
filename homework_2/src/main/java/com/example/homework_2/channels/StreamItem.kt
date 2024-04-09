package com.example.homework_2.channels

data class StreamItem(
    val id: Int,
    val name: String,
    val subscribe: Boolean,
    val topics: MutableList<TopicItem>
)