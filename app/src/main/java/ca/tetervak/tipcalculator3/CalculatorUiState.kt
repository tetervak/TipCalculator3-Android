package ca.tetervak.tipcalculator3

import ca.tetervak.tipcalculator3.model.ServiceQuality

data class CalculatorUiState (
    val serviceCost : String = "",
    val serviceQuality: ServiceQuality = ServiceQuality.GOOD,
    val roundUpTip: Boolean = true,
    val tipAmount: Double = 0.0,
    val billTotal: Double = 0.0
)
