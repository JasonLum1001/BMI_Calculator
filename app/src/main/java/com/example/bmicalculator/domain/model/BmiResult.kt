package com.example.bmicalculator.domain.model

data class BmiResult(
    val bmiValue: Double
) {
    val bmiCategory = when {
        bmiValue < 18.5 -> BmiCategory.UNDERWEIGHT
        bmiValue < 25.0 -> BmiCategory.NORMAL
        bmiValue < 30.0 -> BmiCategory.OVERWEIGHT
        bmiValue < 35.0 -> BmiCategory.OBESE_CLASS_1
        bmiValue < 40.0 -> BmiCategory.OBESE_CLASS_2
        else -> BmiCategory.OBESE_CLASS_3
    }
}

enum class BmiCategory {
    UNDERWEIGHT,
    NORMAL,
    OVERWEIGHT,
    OBESE_CLASS_1,
    OBESE_CLASS_2,
    OBESE_CLASS_3,
}
