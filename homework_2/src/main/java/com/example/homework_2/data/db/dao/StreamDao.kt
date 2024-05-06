package com.example.homework_2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework_2.data.db.entity.StreamDbItem
import com.example.homework_2.data.db.entity.TopicDbItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StreamDao {

    @Query("SELECT * from stream")
    fun getAll(): Flow<List<StreamDbItem>>

    @Query("SELECT * from stream WHERE is_sub == 1")
    fun getSub(): Flow<List<StreamDbItem>>

    @Query("SELECT * from topic where stream_id = :streamId")
    fun getTopics(streamId: Int): Flow<List<TopicDbItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(streams: List<StreamDbItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSub(streams: List<StreamDbItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(topics: List<TopicDbItem>)
}