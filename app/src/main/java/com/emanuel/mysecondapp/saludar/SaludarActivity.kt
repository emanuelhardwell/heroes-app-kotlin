package com.emanuel.mysecondapp.saludar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.emanuel.mysecondapp.R

class SaludarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludar)

        val editTextNombre= findViewById<EditText>(R.id.editTextNombre)
        val btnSaluda= findViewById<Button>(R.id.btnSaluda)


        btnSaluda.setOnClickListener {
            val name= editTextNombre.text.toString()

            if (name.isNotEmpty()){
                navigateToSaludarResponse(name)
            }
            val toast= Toast.makeText(this, "Ingresa un nombre para continuar !", Toast.LENGTH_LONG)
            toast.show()

           // Log.INFO("No hay info en el input")
        }


    }

    fun navigateToSaludarResponse(name: String){
        val intent= Intent(this, SaludarResponseActivity::class.java)
        intent.putExtra("SALUDO", name)
        startActivity(intent)
    }
}