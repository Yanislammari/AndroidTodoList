package com.example.testsetup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), TodoOnclickListener {
    lateinit var todosListRecyclerVew: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActivityViews()
    }

    private fun setupActivityViews(){
        this.todosListRecyclerVew = findViewById(R.id.todo_list_rv)


        //Setup RV Adapter
        val todoAdapter = TodoListAdapter(arrayListOf(
            Todo("La note de Rishi", "17/05/2024", false),
            Todo("La note de Rishi", "17/05/2024", false),
            Todo("La note de Rishi", "17/05/2024", false),
            Todo("La note de Rishi", "17/05/2024", false),
            Todo("La note de Rishi", "17/05/2024", false),
            Todo("La note de Rishi", "17/05/2024", false),
            Todo("La note de Rishi", "17/05/2024", false)
        ), this)

        //Setup Linear Layout Manager
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        this.todosListRecyclerVew.layoutManager = linearLayoutManager
        this.todosListRecyclerVew.adapter = todoAdapter
    }

    override fun displayTodoDetail(todo: Todo) {
        Intent(this, TodoDetailsActivity::class.java).also{
            startActivity(it)
        }
    }
}

interface TodoOnclickListener{
    fun displayTodoDetail(todo: Todo)
}