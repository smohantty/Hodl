package com.finance.hodl

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.finance.hodl.data.repository.ExchangeRepository
import com.finance.hodl.designsystem.component.HodlBackground
import com.finance.hodl.designsystem.theme.HodlTheme
import com.finance.hodl.feature.bot.BotsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HodlTheme {
                HodlBackground {
                    Column {
                        TestScreen()
                        TestScreen("ETH")
                    }

                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TestScreen(
    ticker: String = "BTC",
    symbol: String = "KRW",
    viewModel: BotsViewModel = hiltViewModel()
) {
    val price = remember { mutableStateOf<BigDecimal>(BigDecimal.ZERO) }

    LaunchedEffect(ticker, symbol) {
        // This ensures the previous collection is canceled and a new one starts with the updated ticker or symbol
        viewModel.observePrice(ticker, symbol).collect { latestPrice ->
            price.value = latestPrice
        }
    }

    Text(text = "Hello : ${price.value}")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        Text(text = "Hello")
}