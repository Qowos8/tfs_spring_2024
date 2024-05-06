package com.example.homework_2.presentation.chat.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer
import javax.inject.Inject

class ChatReducer @Inject constructor() : ScreenDslReducer<
        ChatEvent, ChatEvent.Ui,
        ChatEvent.Domain, ChatState,
        ChatEffect,
        ChatCommand>(
    ChatEvent.Ui::class,
    ChatEvent.Domain::class
) {
    override fun Result.internal(event: ChatEvent.Domain) {
        when (event) {
            ChatEvent.Domain.Loading -> state { ChatState.Loading }

            is ChatEvent.Domain.Error -> state { ChatState.Error(event.error) }

            is ChatEvent.Domain.CacheSuccess -> state { ChatState.CacheSuccess(event.value) }

            is ChatEvent.Domain.UpdateSuccess -> state { ChatState.NetworkSuccess(event.value) }

            is ChatEvent.Domain.CacheEmpty -> state { ChatState.CacheEmpty }

            is ChatEvent.Domain.CacheLoaded -> state {ChatState.CacheLoaded}
        }
    }

    override fun Result.ui(event: ChatEvent.Ui) = when (event) {
        is ChatEvent.Ui.LoadMessages -> commands { +ChatCommand.GetDBMessages(event.topicName, event.streamId)}
        is ChatEvent.Ui.AddReaction -> commands{ +ChatCommand.AddReaction(event.messageId, event.emojiName)}
        is ChatEvent.Ui.DeleteReaction -> commands{ +ChatCommand.DeleteReaction(event.messageId, event.emojiName)}
        is ChatEvent.Ui.RegisterEvent -> commands{ +ChatCommand.RegisterEvent }
        is ChatEvent.Ui.SendMessage -> commands{ +ChatCommand.SendMessage(event.streamName, event.topicName, event.content)}
        is ChatEvent.Ui.UpdateMessages -> commands { +ChatCommand.UpdateMessages(event.topicName, event.streamName, event.streamId, event.nextCount) }
    }
}