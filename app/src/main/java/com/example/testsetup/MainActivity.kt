package com.example.testsetup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.regex.Matcher
import java.util.regex.Pattern

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

fun verifyPassword(motDePasse: String): List<String> {
    val erreurs = mutableListOf<String>()

    // Vérifier la longueur minimale
    if (motDePasse.length > 6) {
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
    if (!passwordMatchesqPattern) {
        erreurs.add("Le mot de passe doit contenir au moins un caractère spécial parmi $caracteresSpeciaux.")
    }

    return erreurs
}