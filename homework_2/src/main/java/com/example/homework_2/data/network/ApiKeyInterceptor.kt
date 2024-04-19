package com.example.homework_2.data.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequest = original.newBuilder().addHeader("Authorization", hardCodeAuth).build()
        return chain.proceed(newRequest)
    }

    private companion object{
        private const val USERNAME = "redtefal@gmail.com"
        private const val API_KEY = "xo8pbGmvXTUre3HnsHiFA1MopcjQrobv"
        private val hardCodeAuth = Credentials.basic(USERNAME, API_KEY)
    }
}