package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable.Factory
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bmi_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.result.observe(this){ binding.resultTextView.text = it}
        viewModel.weight.observe(this){ binding.weightEditText.text}
        viewModel.height.observe(this){ binding.heightEditText.text }




        binding.calculateButton.setOnClickListener {
                calculate()
        }

        binding.clearAll.setOnClickListener {
             clear()
        }
    }

    fun clear(){
        binding.weightEditText.text.clear()
        binding.heightEditText.text.clear()
        binding.resultTextView.text = ""

    }

    fun calculate(){

        val weight = binding.weightEditText.text.toString()
        val height = binding.heightEditText.text.toString()
        val msg = "Fill the required Categories"

        if (weight == "" || height == "" ){

            Toast.makeText(this@MainActivity,msg, Toast.LENGTH_LONG ).show()


        } else {

            viewModel.calculateBMI(weight.toFloat(), height.toFloat())

            val bmi = viewModel.calculateBMI(weight.toFloat(), height.toFloat())
            val bmiCategory = viewModel.getBMICategory(bmi)
            val resultText = "BMI: $bmi\nCategory: $bmiCategory"
            binding.resultTextView.text = resultText
        }
    }
}


