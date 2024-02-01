package com.finance.hodl.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finance.hodl.view.HodlApp
import com.finance.hodl.view.theme.HodlTheme
import kotlinx.serialization.Serializable

@Serializable
data class Project(val name: String, val language: String)

class HodlMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SUV","HodlMainActivity::onCreate")

        setContent {
            //val darkTheme = shouldUseDarkTheme(uiState)
            val darkTheme = false

            // Turn off the decor fitting system windows, which allows us to handle insets,
            // including IME animations, and go edge-to-edge
            // This also sets up the initial system bar style based on the platform theme
            enableEdgeToEdge()

            // Update the edge to edge configuration to match the theme
            // This is the same parameters as the default enableEdgeToEdge call, but we manually
            // resolve whether or not to show dark theme using uiState, since it can be different
            // than the configuration's dark theme value based on the user preference.
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

            HodlTheme(
                darkTheme = darkTheme,
                androidTheme = true,
                disableDynamicTheming = true,
            ) {
                HodlApp( )
            }
        }
    }
}


/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

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