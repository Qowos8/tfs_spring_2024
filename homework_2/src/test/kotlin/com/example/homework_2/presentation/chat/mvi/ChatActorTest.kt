package com.example.homework_2.presentation.chat.mvi

import com.example.homework_2.data.network.model.event.Events
import com.example.homework_2.data.network.model.event.NarrowItem
import com.example.homework_2.data.network.model.event.RegisterResponse
import com.example.homework_2.domain.entity.MessageItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ChatActorTest {

    private lateinit var actor: ChatActor

    @Test
    fun `execute WHEN command is GetMessages AND request is successful THEN return Success event`() = runTest {
        val streamId = 55355
        val topicName = "test"

        setupActor(
            getMessages = { streamId, topicName -> getSuccessMessages(streamId, topicName) }
        )

        val resultFlow = actor.execute(ChatCommand.GetDBMessages(topicName, streamId))

        val result = resultFlow.first()

        assert(result is ChatEvent.Domain.CacheSuccess)
        result as ChatEvent.Domain.CacheSuccess
        assert(result.value.equals(getSuccessMessages(streamId, topicName)))
    }

    private fun setupActor(getMessages: (streamId: Int, topicName: String) -> List<MessageItem>) {
        actor = ChatActor(
            sendMessageUseCase = { _, _, _ -> },
            sendReactionUseCase = { _, _ -> },
            deleteReactionUseCase = { _, _ -> },
            registerEventUseCase = { _, _ -> RegisterResponse("", 0) },
            trackEventUseCase = { _, _ -> Events(emptyList()) },
            getMessagesUseCase = { streamId: Int, topicName: String ->
                flowOf(
                    getMessages(
                        streamId,
                        topicName
                    )
                )
            },
            updateMessagesUseCase = { _, _, _, _ -> emptyList() },
            getNextMessages = { _, _, _ -> },
        )
    }

    private fun getSuccessMessages(streamId: Int, topicName: String): List<MessageItem> {
        return listOf(
            MessageItem(
                id = streamId,
                topicName = topicName,
            )
        )
    }

//    @Test
//    fun `execute WHEN command is UpdateMessages AND request is not Empty THEN return UpdateSuccess event`(){
//        val narrow = listOf(
//            NarrowItem("general", "stream"),
//            NarrowItem("testing", "topic"))
//    }
}