package com.example.homework_2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.channels.OnChildClickListener
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.people.OnUserClickListener
import com.example.homework_2.people.PeopleItem
import com.github.terrakok.cicerone.androidx.AppNavigator


class MainActivity : AppCompatActivity(), OnChildClickListener,
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

    override fun onTopicClicked(topic: TopicItem) {
        applicationInstance.router.navigateTo(Screens.Chat())
        DataHolder.topicData = topic
    }

    override fun onUserClicked(user: PeopleItem) {
        applicationInstance.router.navigateTo(Screens.AnotherProfile())
        DataHolder.userData = user
    }

    object DataHolder {
        var userData: PeopleItem? = null
        var topicData: TopicItem? = null
    }
}
