package com.example.homework_2.test

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.homework_2.Application
import com.example.homework_2.di.TestAppComponent
import com.example.homework_2.di.TestAppComponentHolder
import com.example.homework_2.mock.MockMessages
import com.example.homework_2.mock.MockMessages.Companion.message
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.screen.ChatActivityScreen
import com.example.homework_2.util.ActivityRuleUtils
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory

@RunWith(AndroidJUnit4::class)
class ChatActivityTests: TestCase() {

    private val logger = LoggerFactory.getLogger(ChatActivityTests::class.java)


    @get:Rule
    val intentsRule = ActivityRuleUtils<ChatActivity>(getIntentChat()){
        val application = ApplicationProvider.getApplicationContext<Application>()
        TestAppComponent(application).inject(this)
    }


    @Test
    fun messagesIsNotEmpty() = run {
        val testAppComponent = TestAppComponentHolder.testAppComponent
        val wireMockRetrofit = testAppComponent.retrofit()
        intentsRule.wiremockRule.message { withSingleMessages() }
        logger.info("WireMock stubbed with single messages.")
        ChatActivityScreen{
            step("проверка на вызов coffee"){
                verify(WireMock.getRequestedFor(MockMessages.urlPattern))
                logger.info("WireMock request verified.")
                Thread.sleep(2000)
            }
            flakySafely{
                recycler.childAt<ChatActivityScreen.CompanionMessengerLayoutItem>(0){
                    step("проверка наличия текста в самом сообщении"){
                        messageTextView.hasText("first message")
                    }
                }
            }
        }
    }

    private fun getIntentChat(): Intent {
        return Intent(ApplicationProvider.getApplicationContext(), ChatActivity::class.java)
    }
}