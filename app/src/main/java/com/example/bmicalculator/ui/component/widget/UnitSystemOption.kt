package com.example.bmicalculator.ui.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.BmiUnitOption
import com.example.bmicalculator.domain.model.BmiUnitSystem


@Composable
fun UnitSystemOption(
    modifier: Modifier = Modifier,
    unitSystem: BmiUnitSystem,
    onUnitClicked: (BmiUnitSystem) -> Unit,
) {
    val options = listOf(
        BmiUnitOption(
            unitSystem = BmiUnitSystem.METRIC,
            text = stringResource(R.string.input_metric)
        ),
        BmiUnitOption(
            unitSystem = BmiUnitSystem.IMPERIAL,
            text = stringResource(R.string.input_imperial)
        )
    )

    SingleChoiceSegmentedButtonRow (
        modifier = modifier
            .background(
                color = colorResource(R.color.teal_50),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        options.forEach { (unit, text) ->
            SegmentedButton(
                selected = unitSystem == unit,
                onClick = { onUnitClicked.invoke(unit) },
                shape = RoundedCornerShape(12.dp),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = colorResource(R.color.teal_90),
                    activeContentColor = colorResource(R.color.color_text_main),
                    activeBorderColor = colorResource(R.color.teal_90),
                    inactiveContainerColor = colorResource(R.color.transparent),
                    inactiveContentColor = colorResource(R.color.white_60),
                    inactiveBorderColor = colorResource(R.color.transparent)
                ),
                icon = {}
            ) {
                Text(text)
            }
        }
    }
}