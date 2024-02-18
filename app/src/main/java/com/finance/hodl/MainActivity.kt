package com.finance.hodl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.finance.hodl.designsystem.theme.HodlTheme
import com.finance.hodl.ui.HodlApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HodlTheme(
                darkTheme = false,
                androidTheme = true,
                disableDynamicTheming = true,
            ) {
                HodlApp()
            }
        }
    }
}
