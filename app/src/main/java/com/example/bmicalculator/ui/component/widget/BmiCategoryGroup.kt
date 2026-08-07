package com.example.bmicalculator.ui.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.BmiCategory
import com.example.bmicalculator.domain.model.BmiResult

@Composable
fun BmiCategoryGroup(
    modifier: Modifier = Modifier,
    bmiResult: BmiResult
) {
    Column(
        modifier = modifier
    ) {
        BmiCategory.entries.forEach { category ->
            BmiCategoryItem(
                bmiCategory = category,
                isSelected = category == bmiResult.bmiCategory
            )
        }
    }
}

@Composable
private fun BmiCategoryItem(
    modifier: Modifier = Modifier,
    bmiCategory: BmiCategory,
    isSelected: Boolean
) {
    val category = when (bmiCategory) {
        BmiCategory.UNDERWEIGHT -> stringResource(R.string.category_underweight)
        BmiCategory.NORMAL -> stringResource(R.string.category_normal)
        BmiCategory.OVERWEIGHT -> stringResource(R.string.category_overweight)
        BmiCategory.OBESE_CLASS_1 -> stringResource(R.string.category_obese_class_1)
        BmiCategory.OBESE_CLASS_2 -> stringResource(R.string.category_obese_class_2)
        BmiCategory.OBESE_CLASS_3 -> stringResource(R.string.category_obese_class_3)
    }

    val range = when (bmiCategory) {
        BmiCategory.UNDERWEIGHT -> stringResource(R.string.category_underweight_range)
        BmiCategory.NORMAL -> stringResource(R.string.category_normal_range)
        BmiCategory.OVERWEIGHT -> stringResource(R.string.category_overweight_range)
        BmiCategory.OBESE_CLASS_1 -> stringResource(R.string.category_obese_class_1_range)
        BmiCategory.OBESE_CLASS_2 -> stringResource(R.string.category_obese_class_2_range)
        BmiCategory.OBESE_CLASS_3 -> stringResource(R.string.category_obese_class_3_range)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) {
                    colorResource(R.color.teal_20)
                } else {
                    colorResource(R.color.transparent)
                },
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 6.dp
            ),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = category,
            color = colorResource(R.color.color_text_secondary),
            fontWeight = if (isSelected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )

        Text(
            text = range,
            color = colorResource(R.color.color_text_secondary),
            fontWeight = if (isSelected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )
    }
}