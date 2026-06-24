package com.example.bmicalculator.ui.model

data class BmiResult(
    val bmiValue: Double,
    val bmiCategory: BmiCategory
)

enum class BmiCategory {
    UNDERWEIGHT,
    NORMAL,
    OVERWEIGHT,
    OBESE
}
