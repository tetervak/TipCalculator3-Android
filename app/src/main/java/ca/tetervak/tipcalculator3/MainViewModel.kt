package ca.tetervak.tipcalculator3

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator3.model.ServiceQuality
import ca.tetervak.tipcalculator3.model.calculateTip

class MainViewModel: ViewModel() {

    private val _uiState: MutableState<CalculatorUiState> =
        mutableStateOf(
            CalculatorUiState()
        )
    val uiState: State<CalculatorUiState> = _uiState

    fun updateServiceCost(serviceCost: String){
        val uiState = uiState.value
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
        _uiState.value = newUiState
    }

    fun updateServiceQuality(serviceQuality: ServiceQuality){
        val uiState = uiState.value
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
        _uiState.value = newUiState
    }

    fun updateRoundUpTip(roundUpTip: Boolean){
        val uiState = uiState.value
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
        _uiState.value = newUiState
    }

    private fun billBeforeTip(serviceCost: String): Double =
        serviceCost.toDoubleOrNull() ?: 0.0
}