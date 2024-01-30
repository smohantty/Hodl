package com.finance.hodl.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finance.hodl.ui.screens.BottomNavigationItem
import com.finance.hodl.ui.screens.HodlHomeScreen
import com.finance.hodl.ui.theme.HodlTheme
import kotlinx.serialization.Serializable

@Serializable
data class Project(val name: String, val language: String)

class HodlMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SUV","HodlMainActivity::onCreate")
        setContent {
            HodlTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val items = listOf(
                        BottomNavigationItem(
                            title = "Home",
                            selectedIcon = Icons.Filled.Home,
                            unselectedIcon = Icons.Default.Home,
                            hasNews = false
                        ),
                        BottomNavigationItem(
                            title = "Bot",
                            selectedIcon = Icons.Filled.Face,
                            unselectedIcon = Icons.Default.Face,
                            hasNews = true,
                            badgeCount = 5
                        ),
                        BottomNavigationItem(
                            title = "Settings",
                            selectedIcon = Icons.Filled.Settings,
                            unselectedIcon = Icons.Default.Settings,
                            hasNews = true
                        )
                    )
                    HodlHomeScreen(items)
//                    RangeInputFields() { low , high , range ->
//                        Log.d("SUV", "low $low , high: $high, range : $range ")
//                    }
                }
            }
        }
    }
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HodlTheme {
        RangeInputFields() { low , high , range ->
            Log.d("SUV", "low $low , high: $high, range : $range ")
        }
    }
}