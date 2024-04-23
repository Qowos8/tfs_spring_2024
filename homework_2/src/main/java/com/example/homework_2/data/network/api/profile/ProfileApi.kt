package com.example.homework_2.data.network.api.profile

import com.example.homework_2.data.network.model.people.UserResponse
import com.example.homework_2.data.network.model.profile.ProfileItemApi
import com.example.homework_2.data.network.model.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {
    @GET("api/v1/users/me")
    suspend fun getUserMe(): ProfileItemApi

    @GET("api/v1/users")
    suspend fun getUsers(): ProfileResponse

    @GET("api/v1/users/{user_id}")
    suspend fun getUser(@Path("user_id") userId: Int): UserResponse
}