package com.example.homework_2.presentation.channels

import com.example.homework_2.data.network.model.TopicItem

interface OnTopicClickListener {

    fun onTopicClicked(topic: TopicItem, streamName: String)

}