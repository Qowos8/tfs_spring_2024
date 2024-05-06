package com.example.homework_2.data.repository_impl

import com.example.homework_2.data.db.dao.StreamDao
import com.example.homework_2.data.db.mapper.db.toDB
import com.example.homework_2.data.db.mapper.domain.toDomain
import com.example.homework_2.data.network.api.channels.ChannelsApi
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.domain.repository.ChannelsRepository
import javax.inject.Inject
import com.example.homework_2.utils.FilterByNamesUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChannelsRepositoryImpl @Inject constructor(
    private val api: ChannelsApi,
    private val dao: StreamDao
): ChannelsRepository {

    override suspend fun searchStream(query: String, initial: List<StreamItem>): List<StreamItem> {
        return FilterByNamesUtils.filterItemsByName(initial, query)
    }

    override fun getAll(): Flow<List<StreamItem>> {
        return dao.getAll().map { it.toDomain() }
    }

    override fun getSub(): Flow<List<StreamItem>> {
        return dao.getSub().map { it.toDomain() }
    }

    override fun getTopics(streamId: Int): Flow<List<TopicItem>> {
        return dao.getTopics(streamId).map { it.toDomain() }
    }

    override suspend fun updateAll() {
        val channelsApi = api.getAllStreams()
        dao.insertAll(channelsApi.subscriptions.map { it.toDB(0)})
    }

    override suspend fun updateSub() {
        val channelsApi = api.getSubStreams()
        dao.insertSub(channelsApi.subscriptions.map { it.toDB(1)})
    }

    override suspend fun updateTopic(streamId: Int) {
        val channelsApi = api.getTopics(streamId).topics.map {it.toDB(streamId)}
        dao.insertTopics(channelsApi)
    }
}