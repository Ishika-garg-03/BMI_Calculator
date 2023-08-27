package com.example.myapplication

import kotlin.math.pow
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        val showResultButton: Button = findViewById(R.id.showResultButton)
        val heightInput: EditText = findViewById(R.id.heightInput)
        val weightInput: EditText = findViewById(R.id.weightInput)
        val resultTextView: TextView = findViewById(R.id.resultText)

        calculateButton.setOnClickListener {
            val heightString = heightInput.text.toString()
            val weightString = weightInput.text.toString()

            if (heightString.isNotEmpty() && weightString.isNotEmpty()) {
                val height = heightString.toDouble()
                val weight = weightString.toDouble()

                val bmi = calculateBMI(weight, height)
               // val bmiCategory = getBMICategory(bmi)

                resultTextView.text = ""

                calculateButton.visibility = View.GONE
            }
        }

        showResultButton.setOnClickListener {
            val heightString = heightInput.text.toString()
            val weightString = weightInput.text.toString()
            val height = heightString.toDouble()
            val weight = weightString.toDouble()
            val bmi = calculateBMI(weight, height)
            val bmiCategory = getBMICategory(bmi)
            resultTextView.text = getString(R.string.bmi_result, bmi, bmiCategory)
            resultTextView.setTextColor(getBMIColor(bmiCategory))
            showResultButton.visibility = View.VISIBLE
            calculateButton.visibility = View.VISIBLE
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height / 100).pow(2)
    }

    private fun getBMICategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }
    }

    private fun getBMIColor(bmiCategory: String): Int {
        return when (bmiCategory) {
            "Underweight" -> ContextCompat.getColor(this, R.color.underweightColor)
            "Normal" -> ContextCompat.getColor(this, R.color.normalColor)
            "Overweight" -> ContextCompat.getColor(this, R.color.overweightColor)
            else -> ContextCompat.getColor(this, R.color.obeseColor)
        }
    }
}
