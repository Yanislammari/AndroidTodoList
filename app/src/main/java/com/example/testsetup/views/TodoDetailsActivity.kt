package com.example.testsetup.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.testsetup.R
import com.example.testsetup.model.Todo
import com.example.testsetup.views.MainActivity.Companion.TODO_MODEL_EXTRA
import org.w3c.dom.Text
import kotlin.math.log

class TodoDetailsActivity : AppCompatActivity() {
    lateinit var todoTitleTextView: TextView
    lateinit var todoDateTextView: TextView
    lateinit var todoDescriptionTextView: TextView
    lateinit var todoDeleteImageView: ImageView
    lateinit var todoEditImageView: ImageView
    lateinit var validateTodoButton: Button
    private lateinit var todoDetailTitle: String
    private lateinit var todoDetailDescription: String
    private lateinit var todoDetailDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)
        this.getIntentExtraData()
        this.setupView()
        this.handleButtonObservation()
    }

    private fun getIntentExtraData(){
        if(this.intent.hasExtra(TODO_MODEL_EXTRA)){
            val todoData = intent.getParcelableExtra<Todo>(TODO_MODEL_EXTRA)!!
            this.todoDetailTitle = todoData.title ?: ""
            this.todoDetailDescription = todoData.description ?: ""
            this.todoDetailDate = todoData.date ?: ""
        }
    }

    private fun setupView(){
        this.todoTitleTextView = findViewById(R.id.todo_detail_title_text_view)
        this.todoTitleTextView.text = this.todoDetailTitle
        this.todoDateTextView = findViewById(R.id.todo_detail_date_text_view)
        this.todoDateTextView.text = this.todoDetailDate
        this.todoDescriptionTextView = findViewById(R.id.todo_detail_description_text_view)
        this.todoDescriptionTextView.text = this.todoDetailDescription
        this.todoDeleteImageView = findViewById(R.id.delete_todo_image_view)
        this.todoEditImageView = findViewById(R.id.edit_todo_image_view)
        this.validateTodoButton = findViewById(R.id.validate_todo_button)
    }

    private fun handleButtonObservation(){
        this.handelTodoDeleting()
        this.handleTodoEditing()
        this.handelTodoValidating()
    }

    private fun handelTodoDeleting(){
        this.todoDeleteImageView.setOnClickListener {

        }
    }

    private fun handleTodoEditing(){
        this.todoEditImageView.setOnClickListener{

        }
    }

    private fun handelTodoValidating(){
        this.todoEditImageView.setOnClickListener{

        }
    }
}