package com.example.bmicalculator.domain.model

data class BmiUnitOption(
    val unitSystem: BmiUnitSystem,
    val text: String
)

enum class BmiUnitSystem {
    METRIC,
    IMPERIAL
}
