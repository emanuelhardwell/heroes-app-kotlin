package com.emanuel.mysecondapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.emanuel.mysecondapp.imccalculator.IMCActivity
import com.emanuel.mysecondapp.saludar.SaludarActivity
import com.emanuel.mysecondapp.superhero.SuperHeroListActivity
import com.emanuel.mysecondapp.todo.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludarApp= findViewById<Button>(R.id.btnSaludarApp)
        btnSaludarApp.setOnClickListener { navigateToSaludarApp() }

        val btnIMCApp= findViewById<Button>(R.id.btnIMCApp)
        btnIMCApp.setOnClickListener { navigateToIMCApp() }

        val btnTodoApp= findViewById<Button>(R.id.btnTodoApp)
        btnTodoApp.setOnClickListener { navigateToTodoApp() }

        val btnSuperHeroApp= findViewById<Button>(R.id.btnSuperHeroApp)
        btnSuperHeroApp.setOnClickListener { navigateToSuperHeroApp() }
    }

    private fun navigateToTodoApp() {
       val intent= Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSaludarApp(){
        val intent= Intent(this, SaludarActivity::class.java)
        startActivity(intent)
    }

    fun navigateToIMCApp(){
        val intent= Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSuperHeroApp(){
        val intent= Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }
}