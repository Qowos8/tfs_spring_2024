package com.example.homework_2

import android.content.Intent
import com.example.homework_2.channels.ChannelsFragment
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.chat.ChatActivity
import com.example.homework_2.people.PeopleFragment
import com.example.homework_2.profile.AnotherProfileActivity
import com.example.homework_2.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Channels() = FragmentScreen { ChannelsFragment() }
    fun Profile() = FragmentScreen { ProfileFragment() }
    fun People() = FragmentScreen { PeopleFragment() }

    fun AnotherProfile() = ActivityScreen {
        Intent(it, AnotherProfileActivity::class.java)
    }
    fun Chat() = ActivityScreen {
        Intent(it, ChatActivity::class.java)
    }
}
