package com.example.homework_2.presentation

import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.chat.mvi.ChatCommand
import com.example.homework_2.presentation.chat.mvi.ChatEvent
import com.example.homework_2.presentation.chat.mvi.ChatHolderState
import com.example.homework_2.presentation.chat.mvi.ChatReducer
import com.example.homework_2.presentation.chat.mvi.LoadingState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ChatReducerTest {
    private val reducer = ChatReducer()

    @Test
    fun `reduce WHEN CacheSuccess THEN LoadMessages THEN CacheLoaded`() {
        val messages = listOf(MessageItem(0, topicName = "", timestamp = 0))
        // Given
        val initialState = ChatHolderState(loadingState = LoadingState.Init)

        // Когда получаем CacheSuccess
        val cacheSuccessEvent = ChatEvent.Domain.CacheSuccess(messages)
        val cacheSuccessResult = reducer.reduce(cacheSuccessEvent, initialState)

        // Тогда state должен быть CacheSuccess
        assertEquals(LoadingState.CacheSuccess, cacheSuccessResult.state.loadingState)
        // И state должен содержать полученные сообщения
        assertEquals(messages, cacheSuccessResult.state.messages)

        // Отправляем ивент LoadMessages
        val loadMessagesEvent = ChatEvent.Ui.LoadMessages
        val loadMessagesResult = reducer.reduce(loadMessagesEvent, cacheSuccessResult.state)

        // Тогда state должен быть CacheSuccess
        assertEquals(LoadingState.CacheSuccess, loadMessagesResult.state.loadingState)
        // Далее GetDBMessages command должна быть заэмичена
        val emittedCommand = loadMessagesResult.commands.firstOrNull() as ChatCommand.GetDBMessages
        assertEquals(cacheSuccessResult.state, emittedCommand.state)

        // Когда получаем CacheLoaded event
        val cacheLoadedEvent = ChatEvent.Domain.CacheLoaded
        val cacheLoadedResult = reducer.reduce(cacheLoadedEvent, loadMessagesResult.state)

        // Тогда state должен быть CacheLoaded
        assertEquals(LoadingState.CacheLoaded, cacheLoadedResult.state.loadingState)
        // Фиксируем отсутствие лишних команд
        assertTrue(cacheLoadedResult.commands.isEmpty())
    }
}