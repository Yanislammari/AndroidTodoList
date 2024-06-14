package com.example.testsetup.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testsetup.R
import com.example.testsetup.model.Todo

class TodoListAdapter(val todoList: List<Todo>, val todoClickHandler: TodoOnclickListener) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoView = LayoutInflater.from(parent.context).inflate(R.layout.todo_cell_layout, parent, false)
        return TodoViewHolder(todoView)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodoData = this.todoList[position] //get the data at the right poqition
        holder.bind(currentTodoData)
        holder.itemView.setOnClickListener{
            todoClickHandler.displayTodoDetail(currentTodoData)
        }
    }

    //class representing the object linked to the XML, of on call of the RV
    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var todoTitle: TextView
        var todoDate: TextView
        var todoCheckBox: CheckBox

        init{
            this.todoTitle = itemView.findViewById(R.id.todo_title_tv)
            this.todoDate = itemView.findViewById(R.id.todo_date_tv)
            this.todoCheckBox = itemView.findViewById(R.id.todo_checkbox_state_cb)
        }

        fun bind(todo: Todo){
            this.todoTitle.text = todo.title
            this.todoDate.text = todo.date
            this.todoCheckBox.isChecked = todo.isChecked
        }
    }
}