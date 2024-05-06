package com.example.homework_2.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "message")
class MessageDbItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("stream_id")
    val streamId: Int,
    @ColumnInfo("user_id")
    val userId: Int? = null,
    @ColumnInfo("user_full_name")
    val userFullName: String? = null,
    @ColumnInfo("topic_name")
    val topicName: String,
    @ColumnInfo("avatar_url")
    val avatarUrl: String? = null,
    @ColumnInfo("content")
    val content: String? = null,
    @ColumnInfo("reactions")
    val reactions: String,
    @ColumnInfo("timestamp")
    val timestamp: Long? = null,
)