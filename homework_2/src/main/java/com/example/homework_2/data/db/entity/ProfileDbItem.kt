package com.example.homework_2.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class ProfileDbItem (
    @PrimaryKey
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("is_active")
    val isActive: Boolean,
    @ColumnInfo("url")
    val url: String?,
    @ColumnInfo("email")
    val email: String?,
)