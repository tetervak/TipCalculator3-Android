package ca.tetervak.tipcalculator3.model

fun calculateTip(
    billBeforeTip: Double,
    serviceQuality: ServiceQuality = ServiceQuality.OK,
    roundUpTip: Boolean = true
): Double {
    var tipAmount = billBeforeTip * tipPercentage(serviceQuality)
    if (roundUpTip) {
        tipAmount = kotlin.math.ceil(tipAmount)
    }
    return tipAmount
}

private fun tipPercentage(serviceQuality: ServiceQuality): Double =
    when (serviceQuality) {
        ServiceQuality.OK -> 0.15
        ServiceQuality.GOOD -> 0.18
        ServiceQuality.AMAZING -> 0.20
    }