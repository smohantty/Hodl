package com.finance.hodl.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.crypto.exchange.CryptoExchange
import com.crypto.exchange.Exchange


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HodlBotsNewScreen(
    navController: NavHostController
) {
    val exchange = CryptoExchange.builder()
        .exchange(Exchange.Binance)
        .build()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = exchange.name())
        Text(text = navController.currentBackStackEntry?.destination?.route?: "" )
    }
}