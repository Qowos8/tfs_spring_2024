package com.example.homework_2.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.homework_2.data.db.entity.MessageDbItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * from message WHERE stream_id = :streamId AND topic_name = :topicName")
    fun getAll(streamId: Int, topicName: String): Flow<List<MessageDbItem>>

    @Query("SELECT COUNT(*) from message WHERE stream_id = :streamId AND topic_name = :topicName ")
    fun getTopicsMessageCount(streamId: Int, topicName: String): Int

    @Query("SELECT * from message WHERE stream_id = :streamId AND topic_name = :topicName ORDER BY timestamp ASC LIMIT 1")
    suspend fun getOldestMessage(streamId: Int, topicName: String): MessageDbItem

    @Delete
    suspend fun deleteOldestMessage(message: MessageDbItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessagesWithLimit(messages: List<MessageDbItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageDbItem)
}