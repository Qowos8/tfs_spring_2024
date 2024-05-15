package com.example.homework_2.test

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.homework_2.Application
import com.example.homework_2.di.DaggerTestAppComponent
import com.example.homework_2.di.TestAppComponent
import com.example.homework_2.di.app.AppComponentHolder
import com.example.homework_2.di.app.AppDependencies
import com.example.homework_2.mock.MockMessages
import com.example.homework_2.mock.MockMessages.Companion.message
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.presentation.chat.di.ChatDependencies
import com.example.homework_2.screen.ChatActivityScreen
import com.example.homework_2.util.ActivityRuleUtils
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChatActivityTests: TestCase() {

    private lateinit var testComponent: TestAppComponent

    @Before
    fun setUp() {
       intentsRule.wiremockRule.start()
    }

    @After
    fun tearDown() {
        intentsRule.wiremockRule.stop()
    }

    @get:Rule
    val intentsRule = ActivityRuleUtils<ChatActivity>(getIntentChat()){
//        val appDependencies = AppDependencies { ApplicationProvider.getApplicationContext() as Application }
//        testComponent = DaggerTestAppComponent.factory().create(appDependencies)
//        AppComponentHolder.appComponent = testComponent
    }

    @Test
    fun isToolbarTitleVisible() = run {
        ChatActivityScreen{
            step("проверяем наличие имени топика в шапке чата"){
                topicName.hasText("Topic: #testing")
            }
            step("проверяем вмдимость тулбара"){
                streamName.isDisplayed()
            }
        }
    }

    @Test
    fun messagesIsNotEmpty() = run {
        intentsRule.wiremockRule.message { withSingleMessages() }
        Thread.sleep(3000)
        ChatActivityScreen{
            step("проверка на вызов coffee"){
//                verify(WireMock.getRequestedFor(MockMessages.urlPattern)
//                    .withQueryParam("anchor", WireMock.equalTo("newest"))
//                    .withQueryParam("num_before", WireMock.equalTo("2"))
//                    .withQueryParam("num_after", WireMock.equalTo("0"))
//                )
                Thread.sleep(2000)
            }
            flakySafely{
                recycler.getSize() > 0
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