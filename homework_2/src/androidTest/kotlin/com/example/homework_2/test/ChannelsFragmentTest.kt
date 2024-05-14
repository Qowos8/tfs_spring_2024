//package com.example.homework_2.test
//
//import androidx.fragment.app.testing.FragmentScenario
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.homework_2.presentation.channels.parent.ChannelsFragment
//import com.github.tomakehurst.wiremock.WireMockServer
//import com.github.tomakehurst.wiremock.client.WireMock
//import junit.framework.TestCase
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class ChannelsFragmentTest: TestCase() {
//
//    private val wireMockServer = WireMockServer()
//
//    @Before
//    override fun setUp() {
//        wireMockServer.start()
//        WireMock.configureFor("localhost", wireMockServer.port())
//    }
//
//    @After
//    override fun tearDown() {
//        wireMockServer.stop()
//    }
//    @Test
//    fun testChannelsAndChatFlow() = run {
//        WireMock.stubFor(
//            WireMock.get(WireMock.urlPathMatching("/assets"))
//                .willReturn(
//                    WireMock.aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBodyFile("channels.json")
//                )
//        )
//        FragmentScenario.launchInContainer(ChannelsFragment::class.java)
//    }
//}
