package com.example.homework_2.network

import com.example.homework_2.channels.StreamResponseAll
import com.example.homework_2.channels.StreamResponseSub
import com.example.homework_2.channels.TopicResponse
import com.example.homework_2.people.PresenceResponse
import com.example.homework_2.profile.ProfileItem
import com.example.homework_2.profile.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkInterface {

    @GET("api/v1/users/me")
    suspend fun getUserMe(): ProfileItem

    @GET("api/v1/users/me/subscriptions")
    suspend fun getSubStreams(): StreamResponseSub

    @GET("api/v1/streams")
    suspend fun getAllStreams(): StreamResponseAll

    @GET("api/v1/users/me/{stream_id}/topics")
    suspend fun getTopics(@Path("stream_id") streamId: Int): TopicResponse

    @GET("api/v1/users")
    suspend fun getUsers(): ProfileResponse

    @GET("api/v1/realm/presence")
    suspend fun getPresence(): PresenceResponse
}