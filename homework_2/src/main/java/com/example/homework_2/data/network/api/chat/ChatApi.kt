package com.example.homework_2.data.network.api.chat

import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.presentation.channels.child.model.chat.message.MessageResponse
import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.data.network.model.event.PresenceResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {

    @POST("api/v1/register")
    suspend fun registerEvent(@Query("fetch_event_types") fetchEventTypes: String? = null,
                              @Query("event_types") eventTypes: String? = null): RegisterResponse

    @GET("api/v1/events")
    suspend fun trackEvent(@Query("queue_id") queueId: String,
                           @Query("last_event_id") lastEventId: Int = -1,
                           @Query("event_queue_longpoll_timeout_seconds")timeout: Int
    ): Events

    @GET("api/v1/messages")
    suspend fun getTopicMessages(
        @Query("anchor") anchor: String = "newest",
        @Query("num_before") numBefore: Int = 20,
        @Query("num_after") numAfter: Int = 0,
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