package com.example.homework_2.data.impl

import com.example.homework_2.data.network.api.channels.ChannelsApi
import com.example.homework_2.data.network.mapper.toDomain
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.domain.repository.ChannelsRepository
import javax.inject.Inject
import com.example.homework_2.utils.FilterByNamesUtils

class ChannelsRepositoryImpl @Inject constructor(
    private val api: ChannelsApi
): ChannelsRepository {
    override suspend fun getAll(): List<StreamItem> {
        return api.getAllStreams().subscriptions.map { it.toDomain() }
    }

    override suspend fun getSub(): List<StreamItem> {
        return api.getSubStreams().subscriptions.map { it.toDomain() }
    }

    override suspend fun getTopics(streamId: Int): List<TopicItem> {
        return api.getTopics(streamId).topics.map { it.toDomain() }

    }

    override suspend fun searchStream(query: String, initial: List<StreamItem>): List<StreamItem> {
        return FilterByNamesUtils.filterItemsByName(initial, query)
    }
}