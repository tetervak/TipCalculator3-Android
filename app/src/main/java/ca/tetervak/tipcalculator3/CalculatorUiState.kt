package ca.tetervak.tipcalculator3

import ca.tetervak.tipcalculator3.model.ServiceQuality

data class CalculatorUiState (
    val serviceCost: String,
    val serviceQuality: ServiceQuality,
    val roundUpTip: Boolean,
    val tipAmount: Double,
    val billTotal: Double
)
