package com.example.bmicalculator.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel  @Inject constructor(): ViewModel() {
    private val _bmiResult: MutableStateFlow<Double?> = MutableStateFlow(null)
    val bmiResult = _bmiResult.asStateFlow()

    fun calculateBMI(height: Double, weight: Double) {
        _bmiResult.value = weight / (height * height)
    }
}