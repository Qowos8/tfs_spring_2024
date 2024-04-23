package com.example.homework_2.presentation.channels.child.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class ChildReducer : ScreenDslReducer<
        ChildEvent, ChildEvent.Ui,
        ChildEvent.Domain, ChildState,
        ChildEffect,
        ChildCommand>(
    ChildEvent.Ui::class,
    ChildEvent.Domain::class
) {
    override fun Result.internal(event: ChildEvent.Domain) {
        when (event) {

            ChildEvent.Domain.All.Loading -> state{ ChildState.StreamState.Loading }
            is ChildEvent.Domain.All.Error -> state{ ChildState.StreamState.Error(event.error)}
            is ChildEvent.Domain.All.Success -> state{ ChildState.StreamState.Success(event.value)}

            ChildEvent.Domain.Sub.Loading -> state{ ChildState.StreamState.Loading}
            is ChildEvent.Domain.Sub.Error -> state{ ChildState.StreamState.Error(event.error)}
            is ChildEvent.Domain.Sub.Success -> state{ ChildState.StreamState.Success(event.value)}

            ChildEvent.Domain.Topic.Loading -> state{ ChildState.TopicState.Loading}
            is ChildEvent.Domain.Topic.Error -> state{ ChildState.TopicState.Error(event.error)}
            is ChildEvent.Domain.Topic.Success -> state{ ChildState.TopicState.Success(event.value)}
        }
    }

    override fun Result.ui(event: ChildEvent.Ui) = when (event) {
        ChildEvent.Ui.LoadStreamAll -> commands { +ChildCommand.LoadAllStream }
        ChildEvent.Ui.LoadStreamSub -> commands { +ChildCommand.LoadSubStream }
        is ChildEvent.Ui.LoadTopic -> commands { +ChildCommand.LoadTopic(event.streamId) }
        is ChildEvent.Ui.SearchStream -> commands { +ChildCommand.SearchStream(event.query) }
    }
}