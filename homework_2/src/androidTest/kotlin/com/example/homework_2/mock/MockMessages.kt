package com.example.homework_2.mock

import com.example.homework_2.presentation.chat.di.ChatDependencies.Impl.retrofit
import com.example.homework_2.util.AssetsUtils.fromAssets
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching
import retrofit2.Retrofit

class MockMessages(
    private val wireMockServer: WireMockServer) {

    private val matcher = WireMock.get(urlPattern)

    fun withSingleMessages() {
        wireMockServer.stubFor(matcher.willReturn(ok(fromAssets("messages/singleMessages.json"))))
    }

    fun withEmptyList() {
        wireMockServer.stubFor(matcher.willReturn(ok("[]")))
    }

    companion object {

        val urlPattern = urlPathMatching("/api/v1/messages")

        fun WireMockServer.message(block: MockMessages.() -> Unit) {
            MockMessages(this).apply(block)
        }
    }
}

