package com.example.homework_2.presentation.channels.child.mvi

sealed class ChildCommand {
    object LoadAllStream : ChildCommand()
    object LoadSubStream : ChildCommand()
    class LoadTopic(val streamId: Int) : ChildCommand()
    class SearchStream(val query: String): ChildCommand()
}