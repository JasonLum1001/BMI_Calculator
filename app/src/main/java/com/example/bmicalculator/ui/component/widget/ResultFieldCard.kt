package com.example.bmicalculator.ui.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
        val cardModifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = colorResource(R.color.teal_100),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = colorResource(R.color.teal_20),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 26.dp, vertical = 16.dp)

        UserData(
            modifier = cardModifier,
            unitSystem = state.unitSystem,
            height = state.height,
            weight = state.weight
        )

        BmiIndicator(
            modifier = cardModifier,
            bmiResult = state.bmiResult
        )

        CommonPrimaryButton(
            text = stringResource(R.string.btn_retry),
            onClick = onReset
        )
    }
}

