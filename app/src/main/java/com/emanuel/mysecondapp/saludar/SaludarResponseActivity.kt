package com.emanuel.mysecondapp.saludar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.emanuel.mysecondapp.R


class SaludarResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludar_response)

        val name: String = intent.extras?.getString("SALUDO").orEmpty()

        val textViewSaludo= findViewById<TextView>(R.id.textViewSaludo)
        textViewSaludo.text="Hola $name eres Bienvenido !!"


    }
}