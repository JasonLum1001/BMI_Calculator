package com.example.bmicalculator.ui.component.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.BmiCategory
import com.example.bmicalculator.domain.model.BmiResult
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BmiIndicator(
    modifier: Modifier = Modifier,
    bmiResult: BmiResult
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.result_label),
            color = colorResource(R.color.teal_100),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            BmiGaugeView(
                bmiValue = bmiResult.bmiValue
            )

            BmiResultView(
                bmiResult = bmiResult
            )
        }
    }
}

@Composable
private fun BmiGaugeView(bmiValue: Double) {
    val progress = ((bmiValue / 40.0).coerceIn(0.0, 1.0)).toFloat()
    val gradientColor = arrayOf(
        0.50f to colorResource(R.color.blue_100),
        0.71f to colorResource(R.color.blue_100),
        0.76f to colorResource(R.color.green_100),
        0.77f to colorResource(R.color.green_100),
        0.82f to colorResource(R.color.yellow_100),
        0.85f to colorResource(R.color.yellow_100),
        0.90f to colorResource(R.color.red_100),
        1.00f to colorResource(R.color.red_100)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.85f)
    ) {
        val strokeWidth = 20.dp.toPx()

        val arcSize = Size(
            width = size.width - strokeWidth,
            height = size.width - strokeWidth
        )

        val topLeft = Offset(
            x = strokeWidth / 2,
            y = strokeWidth / 2
        )

        val gradientBrush = Brush.sweepGradient(
            colorStops = gradientColor,
            center = Offset(x = size.width / 2f, y = size.height)
        )

        // Draw BMI range arc
        drawArc(
            brush = gradientBrush,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            ),
            size = arcSize,
            topLeft = topLeft
        )


        // Indicator position
        val angle = 180f + (180f * progress)

        val radius = (arcSize.width / 2)

        val center = Offset(
            x = size.width / 2,
            y = size.height - 10.dp.toPx()
        )

        val indicatorX =
            center.x + cos(Math.toRadians(angle.toDouble())).toFloat() * radius

        val indicatorY =
            center.y + sin(Math.toRadians(angle.toDouble())).toFloat() * radius


        drawCircle(
            color = Color.White,
            radius = 10.dp.toPx(),
            center = Offset(
                indicatorX,
                indicatorY
            )
        )

        drawCircle(
            color = Color.Black,
            radius = 6.dp.toPx(),
            center = Offset(
                indicatorX,
                indicatorY
            )
        )
    }
}

@Composable
private fun BmiResultView(
    bmiResult: BmiResult
) {
    val bmiValue = bmiResult.bmiValue
    val bmiCategory = when (bmiResult.bmiCategory) {
        BmiCategory.UNDERWEIGHT -> stringResource(R.string.category_underweight)
        BmiCategory.NORMAL -> stringResource(R.string.category_normal)
        BmiCategory.OVERWEIGHT -> stringResource(R.string.category_overweight)
        BmiCategory.OBESE_CLASS_1 -> stringResource(R.string.category_obese_class_1)
        BmiCategory.OBESE_CLASS_2 -> stringResource(R.string.category_obese_class_2)
        BmiCategory.OBESE_CLASS_3 -> stringResource(R.string.category_obese_class_3)
    }

    Column(
        modifier = Modifier.padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "%.1f".format(bmiValue),
            color = colorResource(R.color.teal_100),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            fontSize = 60.sp
        )

        Text(
            text = bmiCategory,
            color = colorResource(R.color.teal_100),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 25.sp
        )
    }
}