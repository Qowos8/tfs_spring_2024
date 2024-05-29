package com.example.homework_2.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.homework_2.TestApplication
import com.example.homework_2.di.DaggerTestAppComponent
import com.example.homework_2.di.app.AppComponentHolder
import com.example.homework_2.presentation.channels.child.ChannelsFragment
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.screen.ChannelsFragmentScreen
import com.example.homework_2.screen.ChannelsFragmentScreen.KStream
import com.example.homework_2.util.ActivityRuleUtils
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChannelsFragmentTest : TestCase() {

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun openStreamList() = run {
        val chatIntent = hasComponent(ChatActivity::class.java.name)

        val args = Bundle().apply {
            putBoolean("Subscribe", true)
        }

        ChannelsFragmentScreen {
            launchFragmentInContainer<ChannelsFragment>(args)
            flakySafely {
                step("проверка наличия элементов в списке") {
                    recyсlerStream.getSize() > 0
                }
                step("кликаем на элемент списка стримов") {
                    recyсlerStream.perform {
                        this.firstChild<KStream> { click() }
                    }
                }
            }
        }
    }
}