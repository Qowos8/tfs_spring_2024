package com.example.homework_2.presentation.chat.mvi

sealed class ChatCommand {
    class GetDBMessages(val topicName: String, val streamId: Int) : ChatCommand()
    class UpdateMessages(
        val topicName: String,
        val streamName: String,
        val streamId: Int,
        val nextCount: Int
    ): ChatCommand()

    class SendMessage(val streamName: String, val topicName: String, val content: String) :
        ChatCommand()

    class AddReaction(val messageId: Int, val emojiName: String) : ChatCommand()
    class DeleteReaction(val messageId: Int, val emojiName: String) : ChatCommand()
    object RegisterEvent : ChatCommand()
}