package com.example.bmicalculator.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bmicalculator.ui.model.BmiCategory
import com.example.bmicalculator.ui.model.BmiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel  @Inject constructor(): ViewModel() {
    private val _bmiResult = MutableStateFlow<BmiResult?>(null)
    val bmiResult: StateFlow<BmiResult?> = _bmiResult.asStateFlow()

    fun calculateBMI(height: Double, weight: Double) {
        val bmi = weight / (height * height)

        _bmiResult.value = BmiResult(
            bmiValue = bmi,
            bmiCategory = getBmiCategory(bmi)
        )
    }

    fun getBmiCategory(bmi: Double): BmiCategory {
        return when {
            bmi < 18.5 -> BmiCategory.UNDERWEIGHT
            bmi < 25.0 -> BmiCategory.NORMAL
            bmi < 30.0 -> BmiCategory.OVERWEIGHT
            else -> BmiCategory.OBESE
        }
    }
}