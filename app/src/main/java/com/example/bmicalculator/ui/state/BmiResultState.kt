package com.example.bmicalculator.ui.state

import com.example.bmicalculator.domain.model.BmiResult
import com.example.bmicalculator.domain.model.BmiUnitSystem

sealed interface BmiResultState {
    data object None: BmiResultState
    data object Loading: BmiResultState
    data class Success(
        val unitSystem: BmiUnitSystem,
        val height: Double,
        val weight: Double,
        val bmiResult: BmiResult
    ): BmiResultState
}