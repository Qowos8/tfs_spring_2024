package com.example.homework_2.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stream")
class StreamDbItem(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("is_sub")
    val isSub: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("color")
    val color: String? = null,
    @ColumnInfo("description")
    val description: String,
)