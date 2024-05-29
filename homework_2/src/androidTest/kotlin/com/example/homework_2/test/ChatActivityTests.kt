package com.example.homework_2.test

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.homework_2.TestApplication
import com.example.homework_2.di.DaggerTestAppComponent
import com.example.homework_2.di.TestAppDependencies
import com.example.homework_2.di.app.AppComponentHolder
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.screen.ChatActivityScreen
import com.example.homework_2.util.ActivityRuleUtils
import com.github.tomakehurst.wiremock.client.WireMock
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChatActivityTests : TestCase() {

    @Before
    fun setUp() {
        intentsRule.wiremockRule.start()
        intentsRule.wiremockRule.stubFor(
            WireMock.any(WireMock.urlPathEqualTo("/api/v1/register"))
                .willReturn(WireMock.aResponse().withStatus(200))
        )
    }

    @After
    fun tearDown() {
        intentsRule.wiremockRule.stop()
    }

    @get:Rule
    val intentsRule = ActivityRuleUtils<ChatActivity>(getIntentChat()) {
        val appDependencies = TestAppDependencies { this as TestApplication }
        val testComponent = DaggerTestAppComponent.factory().create(appDependencies)
        AppComponentHolder.appComponent = testComponent
    }

    @Test
    fun isToolbarTitleVisible() = run {
        ChatActivityScreen {
            step("проверяем наличие имени топика в шапке чата") {
                topicName.hasText("Topic: #testing")
            }
            step("проверяем вмдимость тулбара") {
                streamName.isDisplayed()
            }
        }
    }

    private fun getIntentChat(): Intent {
        return Intent(ApplicationProvider.getApplicationContext(), ChatActivity::class.java)
            .putExtra("streamName", "general")
            .putExtra("topicName", "testing")
            .putExtra("streamId", 0)
    }
}