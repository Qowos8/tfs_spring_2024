package com.example.homework_2.presentation.channels.child.mvi

sealed class ChannelsCommand {
    object LoadAllStream : ChannelsCommand()
    object LoadSubStream : ChannelsCommand()
    class LoadTopic(val streamId: Int) : ChannelsCommand()
    class SearchStream(val query: String): ChannelsCommand()
    class CreateStream(val name: String, val description: String = ""): ChannelsCommand()

    object LoadDBAllStream: ChannelsCommand()
    object LoadDBSubStream: ChannelsCommand()
    class LoadDBTopics(val streamId: Int): ChannelsCommand()
}