package com.example.homework_2.network

import com.example.homework_2.channels.StreamResponseAll
import com.example.homework_2.channels.StreamResponseSub
import com.example.homework_2.channels.TopicResponse
import com.example.homework_2.people.PresenceResponse
import com.example.homework_2.profile.ProfileItem
import com.example.homework_2.profile.ProfileResponse
import com.example.homework_2.profile.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("api/v1/users/{user_id}")
    suspend fun getUser(@Path("user_id") userId: Int): UserResponse

    @POST("api/v1/register")
    suspend fun registerEvent(@Query("event_queue_longpoll_timeout_seconds")timeout: Int,
                              @Query("fetch_event_types") fetchEventTypes:String? = null,
                              @Query("event_types") eventTypes: String? = null): RegisterResponse

    @GET("api/v1/events")
    suspend fun trackEvent(@Query("queue_id") queueId: String,
                           @Query("last_event_id") lastEventId: Int
    ): Events

    @GET("api/v1/messages")
    suspend fun getTopicMessages(
        @Query("anchor") anchor: String = "newest",
        @Query("num_before") numBefore: Int = 100,
        @Query("num_after") numAfter: Int = 10,
        @Query("narrow") narrow: String? = null,
    ): MessageResponse

    @POST("api/v1/messages")
    suspend fun sendMessage(
        @Query("type") type: String = "stream",
        @Query("to") stream: String,
        @Query("topic") topic: String,
        @Query("content") message: String
    )

    @POST("api/v1/messages/{message_id}/reactions")
    suspend fun addEmoji(
        @Path("message_id")messageId: Int,
        @Query("emoji_name")emoji_name: String,)

    @DELETE("api/v1/messages/{message_id}/reactions")
    suspend fun deleteEmoji(
        @Path("message_id")messageId: Int,
        @Query("emoji_name")emoji_name: String)
}