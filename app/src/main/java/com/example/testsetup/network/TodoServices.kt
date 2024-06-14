package com.example.testsetup.network

import com.example.testsetup.model.TodoDto
import retrofit2.Call
import retrofit2.http.GET

interface TodoServices {
    @GET("/RamzyK/demo/todos") 
    fun getTodos(): Call<List<TodoDto>>
}