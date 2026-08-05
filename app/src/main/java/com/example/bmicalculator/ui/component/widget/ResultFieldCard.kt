package com.example.bmicalculator.ui.component.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.state.BmiResultState

@Composable
fun ResultFieldCard(
    modifier: Modifier = Modifier,
    state: BmiResultState.Success,
    onReset: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UserData(
            unitSystem = state.unitSystem,
            height = state.height,
            weight = state.weight
        )

        BmiIndicator(
            bmiResult = state.bmiResult
        )

        ResetButton(
            onClick = onReset
        )
    }
}

@Composable
private fun ResetButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.teal_90),
            contentColor = colorResource(R.color.color_text_main)
        ),
        onClick = onClick
    ) {
        Text(stringResource(R.string.btn_reset))
    }
}