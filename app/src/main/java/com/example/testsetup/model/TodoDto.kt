package com.example.testsetup.model

data class TodoDto(
    val completed: Boolean,
    val description: String,
    val due_date: String,
    val id: Int,
    val title: String
)
