package com.finance.hodl.view.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.finance.hodl.view.screens.HodlBotsNewScreen
import com.finance.hodl.view.screens.HodlHomeBithumbScreen
import com.finance.hodl.view.screens.HodlSettingsMainScreen

@Composable
fun HodlNavHost(
    navController: NavHostController,
    route: String
) {
    NavHost(
        navController = navController,
        startDestination = route,
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
