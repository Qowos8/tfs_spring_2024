package com.example.homework_2.screen

import com.example.homework_2.R
import com.example.homework_2.presentation.channels.child.ChildFragment
import com.kaspersky.kaspresso.screens.KScreen

object ChannelsFragmentScreen: KScreen<ChannelsFragmentScreen>() {
    override val layoutId: Int = R.layout.expandable_fragment
    override val viewClass: Class<*> = ChildFragment::class.java


}