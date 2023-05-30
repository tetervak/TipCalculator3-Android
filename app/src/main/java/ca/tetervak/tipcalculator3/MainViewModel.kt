package ca.tetervak.tipcalculator3

import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator3.model.ServiceQuality
import ca.tetervak.tipcalculator3.model.calculateTip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _uiStateFlow: MutableStateFlow<CalculatorUiState> =
        MutableStateFlow(
            CalculatorUiState(
                serviceCost = "",
                serviceQuality = ServiceQuality.GOOD,
                roundUpTip = true,
                tipAmount = 0.0,
                billTotal = 0.0
            )
        )
    val uiStateFlow: StateFlow<CalculatorUiState> = _uiStateFlow

    fun updateServiceCost(serviceCost: String){
        val billBeforeTip = billBeforeTip(serviceCost)
        _uiStateFlow.update { uiState ->
            val tipAmount = calculateTip(
                billBeforeTip = billBeforeTip,
                serviceQuality = uiState.serviceQuality,
                roundUpTip = uiState.roundUpTip
            )
            uiState.copy(
                serviceCost = serviceCost,
                tipAmount = tipAmount,
                billTotal = billBeforeTip + tipAmount
            )
        }
    }

    fun updateServiceQuality(serviceQuality: ServiceQuality){
        _uiStateFlow.update { uiState ->
            val billBeforeTip = billBeforeTip(uiState.serviceCost)
            val tipAmount = calculateTip(
                billBeforeTip = billBeforeTip,
                serviceQuality = serviceQuality,
                roundUpTip = uiState.roundUpTip
            )
            uiState.copy(
                serviceQuality = serviceQuality,
                tipAmount = tipAmount,
                billTotal = billBeforeTip + tipAmount
            )
        }
    }

    fun updateRoundUpTip(roundUpTip: Boolean){
        _uiStateFlow.update { uiState ->
            val billBeforeTip = billBeforeTip(uiState.serviceCost)
            val tipAmount = calculateTip(
                billBeforeTip = billBeforeTip,
                serviceQuality = uiState.serviceQuality,
                roundUpTip = roundUpTip
            )
            uiState.copy(
                roundUpTip = roundUpTip,
                tipAmount = tipAmount,
                billTotal = billBeforeTip + tipAmount
            )
        }
    }

    private fun billBeforeTip(serviceCost: String): Double =
        serviceCost.toDoubleOrNull() ?: 0.0
}