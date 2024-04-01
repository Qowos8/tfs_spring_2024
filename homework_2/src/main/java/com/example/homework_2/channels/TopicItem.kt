package com.example.homework_2.channels

data class TopicItem (
    val name : String,
    val id: Int,
    val messageCount: Int,
    var color: Int? = null,
    var parentId: Long,
    val parentName: String
)