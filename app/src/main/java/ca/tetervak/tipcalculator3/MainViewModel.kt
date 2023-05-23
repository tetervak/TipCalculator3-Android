package ca.tetervak.tipcalculator3

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator3.model.ServiceQuality
import ca.tetervak.tipcalculator3.model.calculateTip

class MainViewModel: ViewModel() {

    private val _calculatorUiState: MutableState<CalculatorUiState> =
        mutableStateOf(
            CalculatorUiState(
                serviceCost = "",
                serviceQuality = ServiceQuality.GOOD,
                roundUpTip = true,
                tipAmount = 0.0,
                billTotal = 0.0
            )
        )
    val calculatorUiState: State<CalculatorUiState> = _calculatorUiState

    fun updateServiceCost(serviceCost: String){
        val uiState = calculatorUiState.value
        val billBeforeTip = billBeforeTip(serviceCost)
        val tipAmount = calculateTip(
            billBeforeTip = billBeforeTip,
            serviceQuality = uiState.serviceQuality,
            roundUpTip = uiState.roundUpTip
        )
        val newUiState = uiState.copy(
            serviceCost = serviceCost,
            tipAmount = tipAmount,
            billTotal = billBeforeTip + tipAmount
        )
        _calculatorUiState.value = newUiState
    }

    fun updateServiceQuality(serviceQuality: ServiceQuality){
        val uiState = calculatorUiState.value
        val billBeforeTip = billBeforeTip(uiState.serviceCost)
        val tipAmount = calculateTip(
            billBeforeTip = billBeforeTip,
            serviceQuality = serviceQuality,
            roundUpTip = uiState.roundUpTip
        )
        val newUiState = uiState.copy(
            serviceQuality = serviceQuality,
            tipAmount = tipAmount,
            billTotal = billBeforeTip + tipAmount
        )
        _calculatorUiState.value = newUiState
    }

    fun updateRoundUpTip(roundUpTip: Boolean){
        val uiState = calculatorUiState.value
        val billBeforeTip = billBeforeTip(uiState.serviceCost)
        val tipAmount = calculateTip(
            billBeforeTip = billBeforeTip,
            serviceQuality = uiState.serviceQuality,
            roundUpTip = roundUpTip
        )
        val newUiState = uiState.copy(
            roundUpTip = roundUpTip,
            tipAmount = tipAmount,
            billTotal = billBeforeTip + tipAmount
        )
        _calculatorUiState.value = newUiState
    }

    private fun billBeforeTip(serviceCost: String): Double =
        serviceCost.toDoubleOrNull() ?: 0.0
}