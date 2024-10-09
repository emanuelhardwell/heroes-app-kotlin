package com.emanuel.mysecondapp.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.emanuel.mysecondapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private lateinit var  cardViewMale: CardView
    private lateinit var  cardViewFemale: CardView
    private lateinit var textViewHeight: TextView
    private lateinit var rangeSliderHeight: RangeSlider

    private lateinit var btnWeightSubstract: FloatingActionButton
    private lateinit var btnWeightAdd: FloatingActionButton
    private lateinit var textViewWeight: TextView

    private lateinit var btnAgeSubstract: FloatingActionButton
    private lateinit var btnAgeAdd: FloatingActionButton
    private lateinit var textViewAge: TextView

    private lateinit var btnCalculate: Button


    private var isMaleSelected : Boolean= true
    private var isFemaleSelected: Boolean = false

    private var currentWeight: Int= 60
    private var currentAge: Int= 18

    private var currentHeight: Int= 120


    companion object {
        const val KEY_RESULT_IMC= "RESULT_IMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents(){
        cardViewMale= findViewById(R.id.cardViewMale)
        cardViewFemale= findViewById(R.id.cardViewFemale)
        textViewHeight= findViewById(R.id.textViewHeight)
        rangeSliderHeight= findViewById(R.id.rangeSliderHeight)

        btnWeightSubstract= findViewById(R.id.btnWeightSubstract)
        btnWeightAdd= findViewById(R.id.btnWeightAdd)
        textViewWeight= findViewById(R.id.textViewWeight)

        btnAgeSubstract=findViewById(R.id.btnAgeSubstract)
        btnAgeAdd=findViewById(R.id.btnAgeAdd)
        textViewAge= findViewById(R.id.textViewAge)

        btnCalculate= findViewById(R.id.btnCalculate)
    }

    private fun initListeners(){
        cardViewMale.setOnClickListener{
            changeGender()
            setGenderColor()
        }
        cardViewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rangeSliderHeight.addOnChangeListener { _, value, _ ->
            //val decimalFormat= DecimalFormat("#.##")
            //val res= decimalFormat.format(value)
            //textViewHeight.text= "$res cm"

            val decimalFormat= DecimalFormat("#.##")
            currentHeight= decimalFormat.format(value).toInt()
            textViewHeight.text= "$currentHeight cm"
        }

        btnWeightSubstract.setOnClickListener {
            setWeightSubstract()
        }
        btnWeightAdd.setOnClickListener {
            setWeightAdd()
        }

        btnAgeSubstract.setOnClickListener {
            setAgeSubstractOrAdd("-")
        }
        btnAgeAdd.setOnClickListener {
            setAgeSubstractOrAdd("+")
        }

        btnCalculate.setOnClickListener {
            val res= getCalculated()
            navigateToResult(res)
        }
    }

    private fun getCalculated():Double {
        val heighInmeters= currentHeight.toDouble()/100 //para convertir la altura que esta en CM a M
        val res= currentWeight / (heighInmeters * heighInmeters)
        val df= DecimalFormat("#.##")
        val resFinal= df.format(res).toDouble()
        Log.i("Result IMC", resFinal.toString())
        return resFinal
    }

    private fun navigateToResult(res: Double) {
        val intent= Intent(this, ResultActivity::class.java)
        intent.putExtra(KEY_RESULT_IMC, res)
        startActivity(intent)
    }

    private fun setWeightSubstract(){
        currentWeight -= 1
        setWeightToTextView()
    }

    private fun setWeightAdd(){
        currentWeight += 1
        setWeightToTextView()
    }

    private fun setWeightToTextView(){
        textViewWeight.text= currentWeight.toString()
    }

    private fun setAgeSubstractOrAdd(operation: String){
        if (operation == "+"){
            currentAge += 1
            setAgeToTextView()
        }else{
            currentAge -=1
            setAgeToTextView()
        }
    }

    private fun setAgeToTextView(){
        textViewAge.text= currentAge.toString()
    }

    private fun setGenderColor(){
        cardViewMale.setCardBackgroundColor(getCardBackgroudColor(isMaleSelected))
        cardViewFemale.setCardBackgroundColor(getCardBackgroudColor(isFemaleSelected))
    }

    private fun getCardBackgroudColor (isSelectedButton: Boolean): Int{
        val colorReference= if (isSelectedButton){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }

       return ContextCompat.getColor(this, colorReference)
    }

    private fun changeGender(){
        isMaleSelected= !isMaleSelected
        isFemaleSelected= !isFemaleSelected
    }

    private fun initUI(){
        //setGenderColor()
        setWeightToTextView()
        setAgeToTextView()
    }
}