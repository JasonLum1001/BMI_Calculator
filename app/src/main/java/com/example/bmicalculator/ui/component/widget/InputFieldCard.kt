package com.example.bmicalculator.ui.component.widget

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.BmiUnitSystem

@Composable
fun InputFieldCard(
    modifier: Modifier = Modifier,
    unitSystem: BmiUnitSystem,
    onUnitChanged: (BmiUnitSystem) -> Unit,
    onCalculate: (BmiUnitSystem, Double, Double) -> Unit
) {
    val context = LocalContext.current

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    val heightUnit = when (unitSystem) {
        BmiUnitSystem.METRIC -> stringResource(R.string.input_height_unit_metric)
        BmiUnitSystem.IMPERIAL -> stringResource(R.string.input_height_unit_imperial)
    }

    val weightUnit = when (unitSystem) {
        BmiUnitSystem.METRIC -> stringResource(R.string.input_weight_unit_metric)
        BmiUnitSystem.IMPERIAL -> stringResource(R.string.input_weight_unit_imperial)
    }

    fun clearInput() {
        height = ""
        weight = ""
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UnitSystemOption(
            modifier = Modifier.fillMaxWidth(),
            unitSystem = unitSystem,
            onUnitClicked = { unitSystem ->
                clearInput()
                onUnitChanged.invoke(unitSystem)
            }
        )

        InputRow(
            label = stringResource(R.string.input_height),
            value = height,
            unit = heightUnit,
            onValueChange = { height = it }
        )

        InputRow(
            label = stringResource(R.string.input_weight),
            value = weight,
            unit = weightUnit,
            onValueChange = { weight = it }
        )

        CommonPrimaryButton(
            text = stringResource(R.string.btn_calculate),
            onClick = {
                val input = validateInput(height, weight, unitSystem)

                if (input == null) {
                    Toast.makeText(
                        context,
                        R.string.err_message,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@CommonPrimaryButton
                }

                val (heightValue, weightValue) = input
                onCalculate(unitSystem, heightValue, weightValue)
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
    val decimalRegex = Regex("^\\d*\\.?\\d{0,2}$")

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.width(72.dp),
            text = label
        )

        TextField(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = {
                if (it.isEmpty() || decimalRegex.matches(it)) {
                    onValueChange(it)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorResource(R.color.white_100),
                unfocusedTextColor = colorResource(R.color.white_100),
                focusedContainerColor = colorResource(R.color.teal_50),
                unfocusedContainerColor = colorResource(R.color.teal_50),
                cursorColor = colorResource(R.color.teal_90),
                focusedIndicatorColor = colorResource(R.color.white_100),
                unfocusedIndicatorColor = colorResource(R.color.white_100),
            )
        )

        Text(
            modifier = Modifier.width(30.dp),
            text = unit
        )
    }
}

private val BmiUnitSystem.heightRange
    get() = when (this) {
        BmiUnitSystem.METRIC -> 0.5..3.0
        BmiUnitSystem.IMPERIAL -> 20.0..120.0
    }

private val BmiUnitSystem.weightRange
    get() = when (this) {
        BmiUnitSystem.METRIC -> 10.0..500.0
        BmiUnitSystem.IMPERIAL -> 22.0..1100.0
    }

private fun validateInput(
    height: String,
    weight: String,
    unit: BmiUnitSystem
): Pair<Double, Double>? {

    val heightValue = height.toDoubleOrNull() ?: return null
    val weightValue = weight.toDoubleOrNull() ?: return null

    if (heightValue !in unit.heightRange) return null
    if (weightValue !in unit.weightRange) return null

    return heightValue to weightValue
}