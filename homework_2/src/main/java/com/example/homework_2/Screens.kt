package com.example.homework_2

import android.content.Intent
import com.example.homework_2.presentation.channels.parent.ChannelsFragment
import com.example.homework_2.presentation.chat.ChatActivity
import com.example.homework_2.presentation.people.PeopleFragment
import com.example.homework_2.presentation.profile.AnotherProfileActivity
import com.example.homework_2.presentation.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Channels() = FragmentScreen { ChannelsFragment() }
    fun Profile() = FragmentScreen { ProfileFragment() }
    fun People() = FragmentScreen { PeopleFragment() }

    fun AnotherProfile() = ActivityScreen {
        Intent(it, AnotherProfileActivity::class.java)
    }
    fun Chat(topicName: String, streamName: String) = ActivityScreen {
        Intent(it, ChatActivity::class.java).apply {
            putExtra("streamName", streamName)
            putExtra("topicName", topicName)
        }
    }
}
