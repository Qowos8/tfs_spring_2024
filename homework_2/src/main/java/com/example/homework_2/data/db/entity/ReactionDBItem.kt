package com.example.homework_2.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "reaction")
data class ReactionDBItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo("message_id")
    val messageId: Int,
    @ColumnInfo("emoji_code")
    val emojiCode: String ,
    @ColumnInfo("emoji_name")
    val emojiName: String ,
    @ColumnInfo("user_id")
    val userId: Int ,
    @ColumnInfo("reaction_type")
    val reactionType: String ,
)

