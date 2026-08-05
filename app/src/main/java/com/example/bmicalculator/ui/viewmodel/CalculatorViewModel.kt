package com.example.bmicalculator.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bmicalculator.domain.model.BmiCategory
import com.example.bmicalculator.domain.model.BmiResult
import com.example.bmicalculator.domain.model.BmiUnitSystem
import com.example.bmicalculator.ui.state.BmiResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel  @Inject constructor(): ViewModel() {

    private val _unitSystem = MutableStateFlow(BmiUnitSystem.METRIC)
    val unitSystem: StateFlow<BmiUnitSystem> = _unitSystem.asStateFlow()

    private val _bmiResultState = MutableStateFlow<BmiResultState>(BmiResultState.None)
    val bmiResultState: StateFlow<BmiResultState> = _bmiResultState.asStateFlow()

    fun updateUnitSystem(unitSystem: BmiUnitSystem) {
        _unitSystem.value = unitSystem
    }

    fun resetState() {
        _bmiResultState.value = BmiResultState.None
    }

    fun calculateBMI(unitSystem: BmiUnitSystem, height: Double, weight: Double) {
        _bmiResultState.value = BmiResultState.Loading

        val bmi = when (unitSystem) {
            BmiUnitSystem.METRIC -> weight / (height * height)
            BmiUnitSystem.IMPERIAL -> weight * 703 / (height * height)
        }

        val bmiResult = BmiResult(
            bmiValue = bmi
        )

        _bmiResultState.value = BmiResultState.Success(
            unitSystem = unitSystem,
            height = height,
            weight = weight,
            bmiResult = bmiResult
        )
    }
}