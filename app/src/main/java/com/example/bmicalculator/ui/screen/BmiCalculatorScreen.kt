package com.example.bmicalculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.viewmodel.CalculatorViewModel
import com.example.bmicalculator.common.component.TitleBar
import com.example.bmicalculator.ui.component.StateView.LoadingStateView
import com.example.bmicalculator.ui.component.widget.InputFieldCard
import com.example.bmicalculator.ui.component.widget.ResultFieldCard
import com.example.bmicalculator.ui.state.BmiResultState

@Composable
fun BmiCalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {

    val uiState by viewModel.bmiResultState.collectAsState()
    val unitSystem by viewModel.unitSystem.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HomeHeader()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.teal_100),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = colorResource(R.color.teal_20),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 26.dp, vertical = 16.dp),
        ) {
            when (val state = uiState) {
                is BmiResultState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LoadingStateView(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is BmiResultState.None -> {
                    InputFieldCard(
                        unitSystem = unitSystem,
                        onUnitChanged = { unitSystem ->
                            viewModel.updateUnitSystem(unitSystem)
                        },
                        onCalculate = { unitSystem, height, weight ->
                            viewModel.calculateBMI(unitSystem, height, weight)
                        }
                    )
                }

                is BmiResultState.Success -> {
                    ResultFieldCard(
                        state = state,
                        onReset = {
                            viewModel.resetState()
                        }
                    )
                }
            }

        }
    }
}

@Composable
private fun HomeHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleBar(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.app_title),
            color = colorResource(R.color.teal_100)
        )
    }
}

