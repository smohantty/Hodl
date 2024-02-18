package com.finance.hodl.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.finance.hodl.ui.HodlAppState

@Composable
fun HodlNavHost(
    appState: HodlAppState,
    startDestination: String = HodlNavGraph.Home.route,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        navigation(
            startDestination = HodlHome.Bithumb.route,
            route = HodlNavGraph.Home.route
        ) {
            composable(HodlHome.Bithumb.route) {
                HodlHomeBithumbScreen(navController = navController)
            }
        }

        navigation(
            startDestination = HodlBots.New.route,
            route = HodlNavGraph.Bots.route
        ) {
            composable(HodlBots.New.route) {
                HodlBotsNewScreen(navController = navController)
            }
        }

        navigation(
            startDestination = HodlSettings.Main.route,
            route = HodlNavGraph.Settings.route
        ) {
            composable(HodlSettings.Main.route) {
                HodlSettingsMainScreen(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HodlBotsNewScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Bots")
        Text(text = navController.currentBackStackEntry?.destination?.route?: "" )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HodlSettingsMainScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Bots")
        Text(text = navController.currentBackStackEntry?.destination?.route?: "" )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HodlHomeBithumbScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Bots")
        Text(text = navController.currentBackStackEntry?.destination?.route?: "" )
    }
}

