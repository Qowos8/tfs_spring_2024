package com.example.homework_2.presentation

import com.example.homework_2.presentation.profile.me.mvi.ProfileCommand
import com.example.homework_2.presentation.profile.me.mvi.ProfileEvent
import com.example.homework_2.presentation.profile.me.mvi.ProfileReducer
import com.example.homework_2.presentation.profile.me.mvi.ProfileState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProfileReducerTest {
    private val reducer = ProfileReducer()

    @Test
    fun `reduce WHEN state is Init AND event is Ui_UpdateUser THEN state is Init AND commands should contain LoadUser`() {
        // Given
        val initialState = ProfileState.Init
        val event = ProfileEvent.Ui.UpdateUser
        // When
        val result = reducer.reduce(event, initialState)
        // Then
        assertEquals(ProfileState.Init, result.state)
        assertTrue(result.commands.contains(ProfileCommand.LoadUser))
        assertEquals(listOf(ProfileCommand.LoadUser), result.commands)
    }

    @Test
    fun `reduce WHEN state is Init AND event is Domain_CacheEmpty THEN state is CacheEmpty`() {
        // Given
        val initialState = ProfileState.Init
        val event = ProfileEvent.Domain.CacheEmpty
        // When
        val result = reducer.reduce(event, initialState)
        // Then
        assertEquals(ProfileState.CacheEmpty, result.state)
        assertTrue(result.commands.isEmpty())
    }
}