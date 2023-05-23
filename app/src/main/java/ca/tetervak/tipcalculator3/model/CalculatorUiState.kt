package ca.tetervak.tipcalculator3.model

data class CalculatorUiState (
    val serviceCost: Double,
    val serviceQuality: ServiceQuality,
    val roundUpTip: Boolean,
    val tipAmount: Double,
    val billTotal: Double
)
