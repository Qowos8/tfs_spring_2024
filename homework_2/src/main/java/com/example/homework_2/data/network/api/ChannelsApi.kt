package com.example.homework_2.data.network.api

import com.example.homework_2.data.network.model.channels.stream.StreamResponseAll
import com.example.homework_2.data.network.model.channels.stream.StreamResponseSub
import com.example.homework_2.data.network.model.channels.topic.TopicResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChannelsApi {

    @POST("api/v1/users/me/subscriptions")
    suspend fun createChannel(
        @Query("subscriptions") name: String,
    )

    @GET("api/v1/users/me/subscriptions")
    suspend fun getSubStreams(): StreamResponseSub

    @GET("api/v1/streams")
    suspend fun getAllStreams(): StreamResponseAll

    @GET("api/v1/users/me/{stream_id}/topics")
    suspend fun getTopics(@Path("stream_id") streamId: Int): TopicResponse
}