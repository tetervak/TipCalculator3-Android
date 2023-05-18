package ca.tetervak.tipcalculator3.model

fun calculateTip(
    serviceCost: Double,
    serviceQuality: ServiceQuality = ServiceQuality.OK,
    roundUpTip: Boolean = true
): TipData {
    var tipAmount = serviceCost * tipPercentage(serviceQuality)
    if (roundUpTip) {
        tipAmount = kotlin.math.ceil(tipAmount)
    }
    return TipData(
        serviceCost = serviceCost,
        serviceQuality = serviceQuality,
        roundUpTip = roundUpTip,
        tipAmount = tipAmount,
        billTotal = serviceCost + tipAmount
    )
}

private fun tipPercentage(serviceQuality: ServiceQuality): Double =
    when (serviceQuality) {
        ServiceQuality.OK -> 0.15
        ServiceQuality.GOOD -> 0.18
        ServiceQuality.AMAZING -> 0.20
    }