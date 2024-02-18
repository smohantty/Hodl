package com.finance.hodl.feature.bot

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HodlBotsRout(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        val titles = listOf("New", "Running", "History")
        TabRow(selectedTabIndex = selectedTabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) },
                )
            }
        }

        when (selectedTabIndex) {
            0 -> HodlBotsNewScreen()
            1 -> RunningScreen()
            2 -> HistoryScreen()
        }

    }
}




@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun HodlBotsNewScreen(
    viewModel: BotsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        RangeInputFields(){a,b,c ->
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun RunningScreen(
    viewModel: BotsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "TODO Running Bots Screen")
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun HistoryScreen(
    viewModel: BotsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "TODO History of all Bots")
    }
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


@Composable
fun RangeInputFields(
    onCalculateClicked: (Int, Int, Int) -> Unit
) {
    var rangeLow by remember { mutableStateOf("") }
    var rangeHigh by remember { mutableStateOf("") }
    var gridCount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = rangeLow,
            onValueChange = { rangeLow = it },
            label = { Text("Range Low") } ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = rangeHigh,
            onValueChange = { rangeHigh = it },
            label = { Text("Range High") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = gridCount,
            onValueChange = { gridCount = it },
            label = { Text("Grid Count") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val low = rangeLow.toIntOrNull() ?: return@Button
                val high = rangeHigh.toIntOrNull() ?: return@Button
                val count = gridCount.toIntOrNull() ?: return@Button

                onCalculateClicked(low, high, count)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Calculate")
        }
    }
}