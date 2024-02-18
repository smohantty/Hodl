package com.finance.hodl.feature.bot

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.math.BigDecimal
import java.util.Currency



@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HodlBotsRout(
    navController: NavHostController
) {
    HodlBotsNewScreen(navController)
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun HodlBotsNewScreen(
    navController: NavHostController,
    viewModel: BotsViewModel = hiltViewModel()
) {
    TrackPrice(flow = viewModel.observePrice("BTC", "KRW"))
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
internal fun TrackPrice(
    ticker: String = "BTC",
    currency: String = "KRW",
    flow: Flow<BigDecimal>
) {
    val price = remember { mutableStateOf<BigDecimal>(BigDecimal.ZERO) }

    LaunchedEffect(ticker, currency) {
        flow.collect {
            price.value = it
        }
    }

    Text(text = "$ticker/$currency : ${price.value}")
}