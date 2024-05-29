package com.example.homework_2.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import com.example.homework_2.Screens
import com.example.homework_2.databinding.ActivityMainBinding
import com.example.homework_2.di.app.AppComponentHolder
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.OnTopicClickListener
import com.example.homework_2.presentation.channels.tab.PagerTabFragment
import com.example.homework_2.presentation.people.OnUserClickListener
import com.example.homework_2.presentation.people.PeopleFragment
import com.example.homework_2.presentation.profile.me.ProfileFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    OnTopicClickListener,
    OnUserClickListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navigator = AppNavigator(this, R.id.nav_host_fragment)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponentHolder.appComponent.injectMainActivity(this)
        setContentView(binding.root)

        navigatorHolder.setNavigator(navigator)
        binding.bottomNavView.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            navigateToFragment(PagerTabFragment(), CHANNELS)
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.people -> {
                    navigateToFragment(PeopleFragment(), PEOPLE)
                    true
                }
                R.id.profile -> {
                    navigateToFragment(ProfileFragment(), PROFILE)
                    true
                }
                R.id.channels -> {
                    navigateToFragment(PagerTabFragment(), CHANNELS)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.executePendingTransactions()

        if (supportFragmentManager.isStateSaved) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val currentFragment = supportFragmentManager.primaryNavigationFragment
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
        }

        var fragmentToShow = supportFragmentManager.findFragmentByTag(tag)
        if (fragmentToShow == null) {
            fragmentToShow = fragment
            fragmentTransaction.add(R.id.nav_host_fragment, fragmentToShow, tag)
        } else {
            fragmentTransaction.show(fragmentToShow)
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentToShow)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitAllowingStateLoss()
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
        var userData: Int = 0
        var topicData: TopicItem = TopicItem("", 0)
    }

    private companion object {
        private const val CHANNELS = "channels"
        private const val PEOPLE = "people"
        private const val PROFILE = "profile"
    }
}
