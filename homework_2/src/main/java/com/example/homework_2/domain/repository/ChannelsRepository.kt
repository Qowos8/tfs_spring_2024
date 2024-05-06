package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {

    suspend fun searchStream(query: String, initial: List<StreamItem>): List<StreamItem>

    fun getAll(): Flow<List<StreamItem>>
    fun getSub(): Flow<List<StreamItem>>
    fun getTopics(streamId: Int): Flow<List<TopicItem>>

    suspend fun updateAll()
    suspend fun updateSub()
    suspend fun updateTopic(streamId: Int)
}