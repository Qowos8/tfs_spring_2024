package com.example.homework_2.presentation.channels

import com.example.homework_2.domain.entity.TopicItem

interface OnTopicClickListener {

    fun onTopicClicked(topic: TopicItem, streamName: String)

}