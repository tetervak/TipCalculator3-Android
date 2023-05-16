package ca.tetervak.tipcalculator3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.tipcalculator3.ui.theme.Pink500
import ca.tetervak.tipcalculator3.ui.theme.TipCalculator3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculator3App()
        }
    }
}

@Preview
@Composable
private fun TipCalculator3App() {
    TipCalculator3Theme {
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
   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(32.dp),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.spacedBy(32.dp)
   ) {
       Text(
           text = "Tip Calculator",
           fontSize = 24.sp,
           color = Pink500
       )
   }
}



