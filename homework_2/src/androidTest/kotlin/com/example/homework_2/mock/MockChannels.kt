package com.example.homework_2.mock

import com.example.homework_2.util.AssetsUtils.fromAssets
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching

class MockChannels(private val wireMockServer: WireMockServer) {

    private val matcher = WireMock.get(urlPattern)

    fun withSingleChannels() {
        wireMockServer.stubFor(matcher.willReturn(ok(fromAssets("channels/singleStream.json"))))
    }

    fun withEmptyList() {
        wireMockServer.stubFor(matcher.willReturn(ok("[]")))
    }

    companion object {

        val urlPattern = urlPathMatching("api/v1/users/me/subscriptions")

        fun WireMockServer.message(block: MockChannels.() -> Unit) {
            MockChannels(this).apply(block)
        }
    }
}

