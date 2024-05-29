package com.example.homework_2.presentation.chat.mvi

sealed class ChatCommand {
    class GetDBMessages(val state: ChatHolderState) : ChatCommand()
    class UpdateMessages(val state: ChatHolderState, val nextCount: Int): ChatCommand()
    class SendMessage(val state: ChatHolderState, val content: String) : ChatCommand()
    class AddReaction(val messageId: Int, val emojiName: String) : ChatCommand()
    class DeleteReaction(val messageId: Int, val emojiName: String) : ChatCommand()
    class RegisterEvent(val state: ChatHolderState) : ChatCommand()
}