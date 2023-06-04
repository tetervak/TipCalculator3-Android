package ca.tetervak.tipcalculator3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.tipcalculator3.model.ServiceQuality
import ca.tetervak.tipcalculator3.model.calculateTip
import ca.tetervak.tipcalculator3.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorApp()
        }
    }
}

@Preview
@Composable
fun TipCalculatorApp() {
    TipCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CalculatorScreen()
        }
    }
}

@Composable
fun CalculatorScreen() {

    val roundUpTip: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    val serviceCost: MutableState<String> = remember {
        mutableStateOf("")
    }

    val serviceQuality: MutableState<ServiceQuality> = remember {
        mutableStateOf(ServiceQuality.GOOD)
    }


    val billBeforeTip = serviceCost.value.toDoubleOrNull() ?: 0.0
    val tipAmount = calculateTip(
        billBeforeTip = billBeforeTip,
        serviceQuality = serviceQuality.value,
        roundUpTip = roundUpTip.value
    )

    val billTotal = billBeforeTip + tipAmount

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.tip_calculator_header),
            fontSize = 24.sp,
            color = colorResource(id = R.color.pink_500)
        )
        CalculatorInputs(
            roundUpTip = roundUpTip.value,
            onChangeOfRoundUpTip = { roundUpTip.value = it },
            serviceCost = serviceCost.value,
            onChangeOfServiceCost = { serviceCost.value = it },
            serviceQuality = serviceQuality.value,
            onChangeOfServiceQuality = { serviceQuality.value = it }
        )
        CalculatorOutputs(tipAmount = tipAmount, billTotal = billTotal)
    }
}

fun formatCurrency(amount: Double): String =
    NumberFormat.getCurrencyInstance().format(amount)


@Composable
fun CalculatorOutputs(tipAmount: Double, billTotal: Double) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.tip_amount_label),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .sizeIn(minWidth = 112.dp)
                        .wrapContentWidth(align = Alignment.End)
                )
                Text(
                    text = formatCurrency(amount = tipAmount),
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.purple_500)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.bill_total_label),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .sizeIn(minWidth = 112.dp)
                        .wrapContentWidth(align = Alignment.End)
                )
                Text(
                    text = formatCurrency(amount = billTotal),
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.purple_500)
                )
            }
        }
    }

}

@Composable
fun CalculatorInputs(
    roundUpTip: Boolean, onChangeOfRoundUpTip: (Boolean) -> Unit,
    serviceCost: String, onChangeOfServiceCost: (String) -> Unit,
    serviceQuality: ServiceQuality, onChangeOfServiceQuality: (ServiceQuality) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            ServiceCostInput(
                serviceCost = serviceCost,
                onChange = onChangeOfServiceCost
            )
            ServiceQualityInput(
                serviceQuality = serviceQuality,
                onChange = onChangeOfServiceQuality
            )
            RoundUpTipInput(
                roundUpTip = roundUpTip,
                onChange = onChangeOfRoundUpTip
            )
        }

    }
}

@Composable
fun RoundUpTipInput(roundUpTip: Boolean, onChange: (Boolean) -> Unit) {

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.round_up_tip_input_label),
            fontSize = 20.sp
        )
        Switch(
            checked = roundUpTip,
            onCheckedChange = onChange,
        )
    }
}

@Composable
fun ServiceQualityInput(serviceQuality: ServiceQuality, onChange: (ServiceQuality) -> Unit) {
    Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
        Text(
            text = stringResource(R.string.service_quality_input_label),
            fontSize = 20.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = serviceQuality == ServiceQuality.AMAZING,
                onClick = { onChange(ServiceQuality.AMAZING) }
            )
            Text(
                text = stringResource(id = R.string.quality_amazing_label),
                fontSize = 18.sp
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = serviceQuality == ServiceQuality.GOOD,
                onClick = { onChange(ServiceQuality.GOOD) }
            )
            Text(
                text = stringResource(id = R.string.quality_good_label),
                fontSize = 18.sp
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = serviceQuality == ServiceQuality.OK,
                onClick = { onChange(ServiceQuality.OK) }
            )
            Text(
                text = stringResource(id = R.string.quality_okay_label),
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceCostInput(serviceCost: String, onChange: (String) -> Unit) {
    TextField(
        label = { Text(text = stringResource(id = R.string.service_cost_label)) },
        value = serviceCost,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        textStyle = TextStyle.Default.copy(
            fontSize = 20.sp,
            color = colorResource(id = R.color.purple_500)
        ),
        modifier = Modifier
            .padding(16.dp)
            .sizeIn(minWidth = 256.dp)
    )
}
