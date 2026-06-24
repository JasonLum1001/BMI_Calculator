package com.example.bmicalculator.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.viewmodel.CalculatorViewModel
import java.util.Locale

@Composable
fun BmiCalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {
    val bmiResult by viewModel.bmiResult.collectAsState()

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputField(
            onButtonClicked = { height, weight ->
                viewModel.calculateBMI(height, weight)
            }
        )

        ResultCard(
            bmiResult = bmiResult
        )
    }
}

@Composable
private fun InputField(
    modifier: Modifier = Modifier,
    onButtonClicked: (Double, Double) -> Unit
) {
    val context = LocalContext.current

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = height,
            onValueChange = { height = it },
            label = { Text(stringResource(R.string.input_height)) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = weight,
            onValueChange = { weight = it },
            label = { Text(stringResource(R.string.input_weight)) }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val weightValue = weight.toDoubleOrNull()
                val heightValue = height.toDoubleOrNull()

                if (weightValue == null ||
                    heightValue == null ||
                    weightValue == 0.0 ||
                    heightValue == 0.0
                ) {
                    Toast.makeText(
                        context,
                        R.string.err_message,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }
                onButtonClicked(heightValue, weightValue)
            }
        ) {
            Text(stringResource(R.string.btn_calculate))
        }
    }
}

@Composable
fun ResultCard(
    modifier: Modifier = Modifier,
    bmiResult: Double?
) {
    bmiResult?.let {
        Text(String.format(Locale.US, "%.2f", it))
    }
}

