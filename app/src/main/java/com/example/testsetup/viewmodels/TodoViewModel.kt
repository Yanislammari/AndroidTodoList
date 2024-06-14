package com.example.testsetup.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.testsetup.model.Todo
import com.example.testsetup.model.TodoDto
import com.example.testsetup.network.TodosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(private val todosRepository: TodosRepository, val context: Context) {
    var todos: MutableLiveData<ArrayList<Todo>> = MutableLiveData()

    fun fetchTodoFromRepository(){
        val todosApiResponse = this.todosRepository.fetchTodos()
        todosApiResponse.enqueue(object : Callback<List<TodoDto>> {
            override fun onFailure(p0: Call<List<TodoDto>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(p0: Call<List<TodoDto>>, response: Response<List<TodoDto>>) {
                val responseBody: List<TodoDto> = response.body() ?: listOf()
                val mappedResponse = responseBody.map{
                    Todo(
                        it.title,
                        it.description,
                        it.due_date,
                        it.completed
                    )
                }
                todos.value = ArrayList(mappedResponse)
            }
        })
    }
}