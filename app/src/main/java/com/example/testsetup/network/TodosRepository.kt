package com.example.testsetup.network

import com.example.testsetup.model.TodoDto
import retrofit2.Call

class TodosRepository(private val todoService: TodoServices) {
    fun fetchTodos(): Call<List<TodoDto>> {
        return todoService.getTodos()
    }
}