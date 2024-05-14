//package com.example.homework_2.test
//
//import android.content.Intent
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.homework_2.mock.MockMessages
//import com.example.homework_2.mock.MockMessages.Companion.coffee
//import com.example.homework_2.presentation.chat.ChatActivity
//import com.example.homework_2.screen.ChatActivityScreen
//import com.github.tomakehurst.wiremock.WireMockServer
//import com.github.tomakehurst.wiremock.client.WireMock
//import com.github.tomakehurst.wiremock.client.WireMock.verify
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration
//import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class ChatActivityTest: TestCase() {
//
//    private lateinit var wireMockServer: WireMockServer
//
//    @Before
//    fun setup() {
//        wireMockServer = WireMockServer(WireMockConfiguration.options().port(8081))
//        wireMockServer.start()
//    }
//
//    @After
//    fun teardown() {
//        wireMockServer.stop()
//    }
//
////    @get:Rule
////    val intentsRule = ActivityRuleUtils<ChatActivity>(getIntentChat()){
////
////    }
//
////    @Test
////    fun isTitleNotEmpty() = run {
////        // самый простой тест на проверку наличия текста в тулбаре чата
////        val scenario = ActivityScenario.launch(ChatActivity::class.java)
////        onView(withId(com.example.homework_2.R.id.topic_name)).check(matches(withText(not(""))))
////        Thread.sleep(2000)
////        scenario.close()
////    }
//
//    @Test
//    fun messagesIsNotEmpty() = run{
//        wireMockServer.coffee { withSingleMessages() }
//        ChatActivityScreen{
//            step("проверка на вызов coffee"){
//                verify(WireMock.getRequestedFor(MockMessages.urlPattern))
//                Thread.sleep(2000)
//            }
//            flakySafely{
//                recycler.childAt<ChatActivityScreen.CompanionMessengerLayoutItem>(0){
//                    step("проверка наличия текста в самом сообщении"){
//                        messageTextView.hasText("first message")
//                    }
//                }
//            }
//        }
//    }
//
//    private fun getIntentChat(): Intent {
//        return Intent(ApplicationProvider.getApplicationContext(), ChatActivity::class.java)
//    }
//}