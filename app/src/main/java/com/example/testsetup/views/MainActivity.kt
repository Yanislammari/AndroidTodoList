package com.example.testsetup.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testsetup.R
import com.example.testsetup.model.Todo
import com.example.testsetup.network.TodoServices
import com.example.testsetup.network.TodosRepository
import com.example.testsetup.viewmodels.TodoViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), TodoOnclickListener {
    // Views
    private lateinit var todosListRecyclerVew: RecyclerView
    // Data
    lateinit var retrofitClient: Retrofit
    lateinit var todoService: TodoServices
    lateinit var todoViewModel: TodoViewModel

    companion object{
        val TODO_MODEL_EXTRA = "TODO_MODEL_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.createRetrofitClient()
        this.createTodoService()
        this.initViewModel()
        this.observeTodoListData()
        this.fetchTodoList()
    }

    private fun setupActivityViews(data: List<Todo>){
        this.todosListRecyclerVew = findViewById(R.id.todo_list_rv)


        //Setup RV Adapter
        val todoAdapter = TodoListAdapter(data, this)

        //Setup Linear Layout Manager
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        this.todosListRecyclerVew.layoutManager = linearLayoutManager
        this.todosListRecyclerVew.adapter = todoAdapter
    }

    // Implementation HTTP Client + Services
    private fun createRetrofitClient(){
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        this.retrofitClient = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com")
            .addConverterFactory(gsonConverter)
            .build()
    }

    private fun createTodoService(){
        this.todoService = this.retrofitClient.create(TodoServices::class.java)
    }

    private fun initViewModel(){
        this.todoViewModel = TodoViewModel(
            TodosRepository(this.todoService),
            this
        )
    }

    // Data fetch and observing
    private fun fetchTodoList(){
        this.todoViewModel.fetchTodoFromRepository()
    }

    private fun observeTodoListData(){
        this.todoViewModel.todos.observe(this){

            this.setupActivityViews(it)
        }
    }

    override fun displayTodoDetail(todo: Todo) {
        Intent(this, TodoDetailsActivity::class.java).also{
            it.putExtra(TODO_MODEL_EXTRA, todo)
            startActivity(it)
        }
    }
}

interface TodoOnclickListener{
    fun displayTodoDetail(todo: Todo)
}

fun verifyPassword(motDePasse: String): List<String> {
    val erreurs = mutableListOf<String>()

    // Vérifier la longueur minimale
    if (motDePasse.length < 6) {
        erreurs.add("Le mot de passe doit contenir au moins 6 caractères.")
    }

    // Vérifier au moins une lettre majuscule
    if (!Regex("[A-Z]").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins une lettre majuscule.")
    }

    // Vérifier au moins une lettre minuscule
    if (!Regex("[a-z]").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins une lettre minuscule.")
    }

    // Vérifier au moins un chiffre
    if (!Regex("\\d").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins un chiffre.")
    }

    // Vérifier au moins un caractère spécial
    val caracteresSpeciaux = "~`!@#\\$%\\^&*\\(\\)-_+=<>?/\\[]\\{}|"

    val pattern: Pattern = Pattern.compile(caracteresSpeciaux)
    val matcher: Matcher = pattern.matcher(motDePasse)
    val passwordMatchesqPattern = matcher.matches()
    if (passwordMatchesqPattern) {
        erreurs.add("Le mot de passe doit contenir au moins un caractère spécial parmi $caracteresSpeciaux.")
    }

    return erreurs
}