package com.example.homework_2.presentation.chat.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class ChatReducer : ScreenDslReducer<
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

            is ChatEvent.Domain.Success -> state { ChatState.Success(event.value) }
        }
    }

    override fun Result.ui(event: ChatEvent.Ui) = when (event) {
        is ChatEvent.Ui.LoadMessages -> commands { +ChatCommand.GetMessages(event.topicName, event.streamName)}
        ChatEvent.Ui.MessagesLoaded -> commands {  }
        is ChatEvent.Ui.AddReaction -> commands{ +ChatCommand.AddReaction(event.messageId, event.emojiName)}
        is ChatEvent.Ui.DeleteReaction -> commands{ +ChatCommand.DeleteReaction(event.messageId, event.emojiName)}
        is ChatEvent.Ui.RegisterEvent -> commands{ +ChatCommand.RegisterEvent }
        is ChatEvent.Ui.SendMessage -> commands{ +ChatCommand.SendMessage(event.streamName, event.topicName, event.content)}
    }
}