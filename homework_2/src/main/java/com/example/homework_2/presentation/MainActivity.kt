package com.example.homework_2.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.Application
import com.example.homework_2.R
import com.example.homework_2.Screens
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.presentation.people.OnUserClickListener
import com.github.terrakok.cicerone.androidx.AppNavigator


class MainActivity : AppCompatActivity(),
    com.example.homework_2.presentation.channels.OnTopicClickListener,
    OnUserClickListener {
    private lateinit var binding: ActivityMainBinding
    private val applicationInstance: Application
        get() = application as Application
    private val navigator = AppNavigator(this, R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applicationInstance.navigatorHolder.setNavigator(navigator)
        binding.bottomNavView.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            applicationInstance.router.navigateTo(Screens.Channels())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.people -> {
                    applicationInstance.router.navigateTo(Screens.People())
                    binding.bottomNavView.visibility = View.VISIBLE
                }

                R.id.profile -> {
                    applicationInstance.router.navigateTo(Screens.Profile())
                    binding.bottomNavView.visibility = View.VISIBLE
                }

                R.id.channels -> {
                    applicationInstance.router.navigateTo(Screens.Channels())
                    binding.bottomNavView.visibility = View.VISIBLE
                }
            }
            true
        }
    }

    override fun onTopicClicked(topic: com.example.homework_2.presentation.channels.TopicItem, streamName: String) {
        applicationInstance.router.navigateTo(Screens.Chat(topic.name, streamName))
        DataHolder.topicData = topic
    }

    override fun onUserClicked(user: Int) {
        applicationInstance.router.navigateTo(Screens.AnotherProfile())
        DataHolder.userData = user
    }

    object DataHolder {
        var userData: Int? = null
        var topicData: com.example.homework_2.presentation.channels.TopicItem? = null
    }
}
