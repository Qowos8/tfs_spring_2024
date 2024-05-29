package com.example.homework_2.presentation

import com.example.homework_2.domain.entity.ProfileItem
import com.example.homework_2.presentation.people.mvi.PeopleCommand
import com.example.homework_2.presentation.people.mvi.PeopleEvent
import com.example.homework_2.presentation.people.mvi.PeopleReducer
import com.example.homework_2.presentation.people.mvi.PeopleState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PeopleReducerTest {
    private val reducer = PeopleReducer()

    @Test
    fun `reduce WHEN CacheEmpty THEN LoadUsers THEN CacheLoaded`() {
        // Given
        val initialState = PeopleState.Init

        // Когда получаем CacheEmpty
        val cacheEmptyEvent = PeopleEvent.Domain.CacheEmpty
        val cacheEmptyResult = reducer.reduce(cacheEmptyEvent, initialState)

        // Тогда state должен быть Loading
        assertEquals(PeopleState.Loading, cacheEmptyResult.state)

        // Фиксируем отсутствие лишних команд
        assertTrue(cacheEmptyResult.commands.isEmpty())

        // Отправляем ивент LoadUsers
        val loadUsersEvent = PeopleEvent.Ui.LoadUsers
        val loadUsersResult = reducer.reduce(loadUsersEvent, cacheEmptyResult.state)

        // Тогда state должен быть Loading
        assertEquals(PeopleState.Loading, loadUsersResult.state)

        // Дальше LoadPeople command должна быть заэмичена
        assertEquals(listOf(PeopleCommand.UpdatePeople), loadUsersResult.commands)

        // Когда получаем CacheSuccess
        val users = emptyList<ProfileItem>()
        val cacheLoadedEvent = PeopleEvent.Domain.CacheSuccess(users)
        val cacheLoadedResult = reducer.reduce(cacheLoadedEvent, loadUsersResult.state)

        // Тогда state должен содержать полученных юзеров
        assertTrue((cacheLoadedResult.state as PeopleState.CacheSuccess).users == users)

        // Фиксиурем отсутствие лишних команд
        assertTrue(cacheLoadedResult.commands.isEmpty())
    }
}