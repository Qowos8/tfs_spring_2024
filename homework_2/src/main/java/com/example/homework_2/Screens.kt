package com.example.homework_2

import android.content.Intent
import android.os.Bundle
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.tab.PagerTabFragment
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.presentation.people.PeopleFragment
import com.example.homework_2.presentation.profile.another.AnotherProfileActivity
import com.example.homework_2.presentation.profile.me.ProfileFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    private const val STREAM_NAME = "streamName"
    private const val TOPIC_NAME = "topicName"
    private const val STREAM_ID = "streamId"

    fun Channels() = FragmentScreen { PagerTabFragment()}
    fun Profile() = FragmentScreen { ProfileFragment() }
    fun People() = FragmentScreen { PeopleFragment() }

    fun AnotherProfile() = ActivityScreen {
        Intent(it, AnotherProfileActivity::class.java)
    }
    fun Chat(topic: TopicItem, streamName: String, streamId: Int) = ActivityScreen {
        Intent(it, ChatActivity::class.java).apply {
            putExtra(STREAM_NAME, streamName)
            putExtra(TOPIC_NAME, topic.name)
            putExtra(STREAM_ID, streamId)
        }
    }
}
