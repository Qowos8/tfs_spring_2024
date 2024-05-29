package com.example.homework_2.presentation.channels.child.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer
import javax.inject.Inject

class ChannelsReducer @Inject constructor() : ScreenDslReducer<
        ChannelsEvent, ChannelsEvent.Ui,
        ChannelsEvent.Domain, ChannelsState,
        ChannelsEffect,
        ChannelsCommand>(
    ChannelsEvent.Ui::class,
    ChannelsEvent.Domain::class
) {
    override fun Result.internal(event: ChannelsEvent.Domain) {
        when (event) {
            is ChannelsEvent.Domain.ErrorCreate -> {ChannelsState.StreamState.Error(event.error)}
            is ChannelsEvent.Domain.All.CacheEmpty -> state{ ChannelsState.StreamState.EmptyCache }
            is ChannelsEvent.Domain.All.Loading -> state {ChannelsState.StreamState.Loading}
            is ChannelsEvent.Domain.All.Error -> state{ ChannelsState.StreamState.Error(event.error)}
            is ChannelsEvent.Domain.All.CacheSuccess -> state{ ChannelsState.StreamState.CacheSuccess(event.value) }

            is ChannelsEvent.Domain.Sub.CacheEmpty -> state{ ChannelsState.StreamState.EmptyCache}
            is ChannelsEvent.Domain.Sub.Loading -> state {ChannelsState.StreamState.Loading}
            is ChannelsEvent.Domain.Sub.Error -> state{ ChannelsState.StreamState.Error(event.error)}
            is ChannelsEvent.Domain.Sub.CacheSuccess -> state{ ChannelsState.StreamState.CacheSuccess(event.value)}

            is ChannelsEvent.Domain.Topic.CacheEmpty -> state{ ChannelsState.TopicState.EmptyCache}
            is ChannelsEvent.Domain.Topic.Error -> state{ ChannelsState.TopicState.Error(event.error)}
            is ChannelsEvent.Domain.Topic.CacheLoaded -> state{ ChannelsState.TopicState.CacheLoaded}
            is ChannelsEvent.Domain.Topic.CacheSuccess -> state{ ChannelsState.TopicState.CacheSuccess(event.value)}
            is ChannelsEvent.Domain.Topic.Loading -> state {ChannelsState.TopicState.Init}
        }
    }

    override fun Result.ui(event: ChannelsEvent.Ui) = when (event) {
        is ChannelsEvent.Ui.UpdateStreamAll -> commands { +ChannelsCommand.LoadAllStream }
        is ChannelsEvent.Ui.UpdateStreamSub -> commands { +ChannelsCommand.LoadSubStream }
        is ChannelsEvent.Ui.UpdateTopic -> commands { +ChannelsCommand.LoadTopic(event.streamId) }
        is ChannelsEvent.Ui.SearchStream -> commands { +ChannelsCommand.SearchStream(event.query) }
        is ChannelsEvent.Ui.LoadDBAll -> commands { +ChannelsCommand.LoadDBAllStream }
        is ChannelsEvent.Ui.LoadDBSub -> commands { +ChannelsCommand.LoadDBSubStream }
        is ChannelsEvent.Ui.LoadDbTopic -> commands { +ChannelsCommand.LoadDBTopics(event.streamId) }
        is ChannelsEvent.Ui.CreateStream -> commands { +ChannelsCommand.CreateStream(event.name, event.description) }
    }
}