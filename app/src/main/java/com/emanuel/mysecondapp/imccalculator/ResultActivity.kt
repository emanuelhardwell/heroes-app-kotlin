package com.emanuel.mysecondapp.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.emanuel.mysecondapp.R

class ResultActivity : AppCompatActivity() {
    private lateinit var textViewResultTitle: TextView
    private lateinit var textViewResultIMC: TextView
    private lateinit var textViewResultDescription: TextView
    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultIMC: Double= intent.extras?.getDouble(IMCActivity.KEY_RESULT_IMC) ?: -1.0
        Log.i("resultIMC", resultIMC.toString())

        initComponents()
        initListeners()
        initResponse(resultIMC)
    }

    private fun initResponse(resultIMC: Double) {
        textViewResultIMC.text= resultIMC.toString()
        when(resultIMC){
           in 0.00..18.50 -> { //peso bajo (insuficiente)
               textViewResultTitle.text= getString(R.string.weightLow)
               textViewResultTitle.setTextColor(ContextCompat.getColor(this,R.color.weightLow))
               textViewResultDescription.text= getResources().getString(R.string.weightLowDescription)

           }
            in 18.51..24.99 -> { //peso normal (saludable)
                textViewResultTitle.text= getString(R.string.weightNormal)
                textViewResultTitle.setTextColor(ContextCompat.getColor(this,R.color.weightNormal))
                textViewResultDescription.text= getResources().getString(R.string.weightNormalDescription)
            }
            in 25.00..29.99 -> { //peso sobrepeso
                textViewResultTitle.text= getString(R.string.weightHigh)
                textViewResultTitle.setTextColor(ContextCompat.getColor(this,R.color.weightHigh))
                textViewResultDescription.text= getResources().getString(R.string.weightHighDescription)
            }
            in 30.00..99.00 -> { //peso obesidad
                textViewResultTitle.text= getString(R.string.weightSuperHigh)
                textViewResultTitle.setTextColor(ContextCompat.getColor(this,R.color.weightSuperHigh))
                textViewResultDescription.text= getResources().getString(R.string.weightSuperHighDescription)
            }else-> { //error
            textViewResultIMC.text = "ERROR imc"
            textViewResultTitle.text = "ERROR"
            textViewResultTitle.setTextColor(ContextCompat.getColor(this, R.color.weightError))
            textViewResultDescription.text = "ERROR"
            }
        }
    }


    private fun initComponents() {
        textViewResultTitle= findViewById(R.id.textViewResultTitle)
        textViewResultIMC= findViewById(R.id.textViewResultIMC)
        textViewResultDescription= findViewById(R.id.textViewResultDescription)
        btnReCalculate= findViewById(R.id.btnReCalculate)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

}