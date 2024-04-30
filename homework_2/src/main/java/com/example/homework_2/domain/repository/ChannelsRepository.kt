package com.example.homework_2.domain.repository

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem

interface ChannelsRepository {
    suspend fun getAll(): List<StreamItem>

    suspend fun getSub(): List<StreamItem>

    suspend fun getTopics(streamId: Int): List<TopicItem>

    suspend fun searchStream(query: String, initial: List<StreamItem>): List<StreamItem>
}