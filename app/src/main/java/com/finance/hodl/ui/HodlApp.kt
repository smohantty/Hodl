package com.finance.hodl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HodlApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Bots") {
            //Screen1(navController)
        }
        composable("Setting") {
            //Screen2(navController)
        }
    }
}
