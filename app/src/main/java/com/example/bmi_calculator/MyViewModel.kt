package com.example.bmi_calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private val _result = MutableLiveData<String>()
    private val _weight = MutableLiveData<String>()
    private val _height = MutableLiveData<String>()
    private val _bmiResult = MutableLiveData<String>()
    private val _getBMICategory = MutableLiveData<String>()

    val bmiResult: LiveData<String> = _bmiResult
    val getBMICategory:LiveData<String> = _getBMICategory

    val result:LiveData<String> = _result
    val weight:LiveData<String> = _weight
    val height:LiveData<String> = _height

    init {

     _result.value = bmiResult.value

    }

    fun calculateBMI(weight: Float, height: Float): Float {

        val heightInMeter = height / 100

        return weight / (heightInMeter * heightInMeter)
    }

    fun getBMICategory(bmi: Float): String {
        // Here are the BMI category classification logic here
        // different ranges are define and the appropriate categories are return based on the BMI value
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal Weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

    }

}