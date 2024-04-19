package com.example.homework_2.presentation.people

data class PeopleItem (
    val id: Int,
    val name: String,
    val mail: String,
    var isAuth: Boolean,
    var isOnline: Boolean
)