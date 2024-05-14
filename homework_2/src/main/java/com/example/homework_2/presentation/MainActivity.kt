package com.example.homework_2.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.R
import com.example.homework_2.Screens
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.di.app.AppComponentHolder
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.OnTopicClickListener
import com.example.homework_2.presentation.people.OnUserClickListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    OnTopicClickListener,
    OnUserClickListener {
    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.nav_host_fragment)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponentHolder.appComponent.injectMainActivity(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigatorHolder.setNavigator(navigator)
        binding.bottomNavView.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            router.navigateTo(Screens.Channels())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.people -> {
                    router.navigateTo(Screens.People())
                    binding.bottomNavView.visibility = View.VISIBLE
                }

                R.id.profile -> {
                    router.navigateTo(Screens.Profile())
                    binding.bottomNavView.visibility = View.VISIBLE
                }

                R.id.channels -> {
                    router.navigateTo(Screens.Channels())
                    binding.bottomNavView.visibility = View.VISIBLE
                }
            }
            true
        }
    }

    override fun onTopicClicked(
        topic: TopicItem,
        streamName: String,
        streamId: Int,
        topicName: String
    ) {
        router.navigateTo(Screens.Chat(topic, streamName, streamId))
        DataHolder.topicData = topic
    }

    override fun onUserClicked(user: Int) {
        router.navigateTo(Screens.AnotherProfile())
        DataHolder.userData = user
    }

    object DataHolder {
        var userData: Int? = null
        var topicData: TopicItem? = null
    }
}
