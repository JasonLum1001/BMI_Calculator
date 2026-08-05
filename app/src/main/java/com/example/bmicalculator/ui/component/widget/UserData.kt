package com.example.bmicalculator.ui.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.BmiUnitSystem

@Composable
fun UserData(
    modifier: Modifier = Modifier,
    unitSystem: BmiUnitSystem,
    height: Double,
    weight: Double
) {
    val (heightUnit, weightUnit) = when (unitSystem) {
        BmiUnitSystem.METRIC -> "m" to "kg"
        BmiUnitSystem.IMPERIAL -> "in" to "lb"
    }

    Row(
        modifier = modifier
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
            .padding(horizontal = 26.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserDataItem(
            icon = Icons.Default.Height,
            label = stringResource(R.string.input_height),
            value = "%.2f $heightUnit".format(height)
        )

        UserDataItem(
            icon = Icons.Default.MonitorWeight,
            label = stringResource(R.string.input_weight),
            value = "%.2f $weightUnit".format(weight)
        )
    }
}

@Composable
private fun UserDataItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = colorResource(R.color.teal_100)
            )

            Text(
                text = label,
                color = colorResource(R.color.teal_100)
            )
        }
        
        Text(
            text = value,
            color = colorResource(R.color.teal_100)
        )
    }
}