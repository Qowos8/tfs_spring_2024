package com.example.homework_2.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic")
class TopicDbItem(
    @ColumnInfo("stream_id")
    val streamId: Int,
    @PrimaryKey
    @ColumnInfo("name")
    val name : String,
    @ColumnInfo("message_count")
    val messageCount: Int,
)