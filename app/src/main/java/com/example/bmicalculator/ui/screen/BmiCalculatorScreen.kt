package com.example.bmicalculator.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.model.BmiResult
import com.example.bmicalculator.ui.viewmodel.CalculatorViewModel
import java.util.Locale

@Composable
fun BmiCalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {
    BoxWithConstraints(modifier = modifier) {
        val isLandscape = maxWidth > maxHeight

        val cardModifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(R.color.white_30),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = colorResource(R.color.white_10),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(25.dp, 16.dp)
        val bmiResult by viewModel.bmiResult.collectAsState()

        if (isLandscape) {
            LandscapeLayout(
                cardModifier = cardModifier,
                bmiResult = bmiResult,
                onCalculate = { height, weight ->
                    viewModel.calculateBMI(height, weight)
                }
            )
        } else {
            PortraitLayout(
                cardModifier = cardModifier,
                bmiResult = bmiResult,
                onCalculate = { height, weight ->
                    viewModel.calculateBMI(height, weight)
                }
            )
        }
    }


}

@Composable
fun PortraitLayout(
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    bmiResult: BmiResult?,
    onCalculate: (Double, Double) -> Unit
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputFieldCard(
            modifier = cardModifier.fillMaxWidth(),
            onButtonClicked = onCalculate
        )

        ResultCard(
            modifier = cardModifier.fillMaxWidth(),
            bmiResult = bmiResult
        )
    }
}

@Composable
fun LandscapeLayout(
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    bmiResult: BmiResult?,
    onCalculate: (Double, Double) -> Unit
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputFieldCard(
            modifier = cardModifier
                .weight(1f)
                .fillMaxHeight(),
            onButtonClicked = onCalculate
        )

        ResultCard(
            modifier = cardModifier
                .weight(1f)
                .fillMaxHeight(),
            bmiResult = bmiResult
        )
    }
}

@Composable
private fun InputFieldCard(
    modifier: Modifier = Modifier,
    onButtonClicked: (Double, Double) -> Unit
) {
    val context = LocalContext.current

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputRow(
            label = stringResource(R.string.input_height),
            value = height,
            unit = stringResource(R.string.input_height_unit),
            onValueChange = { height = it }
        )

        InputRow(
            label = stringResource(R.string.input_weight),
            value = weight,
            unit = stringResource(R.string.input_weight_unit),
            onValueChange = { weight = it }
        )

        CalculateButton(
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
                    return@CalculateButton
                }
                onButtonClicked(heightValue, weightValue)
            }
        )
    }
}

@Composable
fun InputRow(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.weight(2f),
            text = label
        )

        TextField(
            modifier = Modifier.weight(5f),
            value = value,
            onValueChange = onValueChange
        )

        Text(
            modifier = Modifier.weight(1f),
            text = unit
        )
    }
}

@Composable
private fun CalculateButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = colorResource(R.color.red),
            contentColor = colorResource(R.color.white_100)
        ),
        onClick = onClick
    ) {
        Text(stringResource(R.string.btn_calculate))
    }
}

@Composable
fun ResultCard(
    modifier: Modifier = Modifier,
    bmiResult: BmiResult?
) {
    bmiResult?.let {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = String.format(Locale.US, "%.2f", it.bmiValue),
                color = colorResource(R.color.red),
                fontSize = 66.sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it.bmiCategory.toString(),
                color = colorResource(R.color.white_80),
                fontSize = 33.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

