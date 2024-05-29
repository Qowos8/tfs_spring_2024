package com.example.homework_2.presentation.chat.mvi

import vivid.money.elmslie.core.store.dsl.ScreenDslReducer
import javax.inject.Inject

class ChatReducer @Inject constructor() : ScreenDslReducer<
        ChatEvent, ChatEvent.Ui,
        ChatEvent.Domain, ChatHolderState,
        ChatEffect,
        ChatCommand>(
    ChatEvent.Ui::class,
    ChatEvent.Domain::class
) {
    override fun Result.internal(event: ChatEvent.Domain) {
        when (event) {
            ChatEvent.Domain.Loading -> state { copy(loadingState = LoadingState.Loading) }

            is ChatEvent.Domain.Error -> state {
                copy(
                    loadingState = LoadingState.Error,
                    errorMessage = event.error
                ) }

            is ChatEvent.Domain.CacheSuccess -> state {
                copy(
                    loadingState = LoadingState.CacheSuccess,
                    messages = event.value
                ) }

            is ChatEvent.Domain.UpdateSuccess -> state {
                copy(
                    loadingState = LoadingState.NetworkSuccess,
                    messages = event.value
                ) }

            is ChatEvent.Domain.CacheEmpty -> state { copy(loadingState = LoadingState.CacheEmpty) }

            is ChatEvent.Domain.CacheLoaded -> state { copy(loadingState = LoadingState.CacheLoaded) }
        }
    }

    override fun Result.ui(event: ChatEvent.Ui) = when (event) {
        is ChatEvent.Ui.Init -> {
            state {
                copy(
                    streamName = event.streamName,
                    topicName = event.topicName,
                    streamId = event.streamId,
                )
            }
        }
        is ChatEvent.Ui.LoadMessages -> commands {
            +ChatCommand.GetDBMessages(state)
        }

        is ChatEvent.Ui.AddReaction -> commands {
            +ChatCommand.AddReaction(event.messageId, event.emojiName)
        }

        is ChatEvent.Ui.DeleteReaction -> commands {
            +ChatCommand.DeleteReaction(event.messageId, event.emojiName)
        }

        is ChatEvent.Ui.RegisterEvent -> commands {
            +ChatCommand.RegisterEvent(state)
        }

        is ChatEvent.Ui.SendMessage -> commands {
            +ChatCommand.SendMessage(state, event.content)
        }

        is ChatEvent.Ui.UpdateMessages -> commands {
            +ChatCommand.UpdateMessages(state, event.nextCount)
        }

        ChatEvent.Ui.LoadingPage -> Unit
    }
}