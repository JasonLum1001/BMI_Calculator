package com.example.bmicalculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.R

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.color_background_main))
    ) { innerPadding ->
        BmiCalculatorScreen(
            modifier = Modifier.padding(innerPadding),
            viewModel = hiltViewModel()
        )
    }
}