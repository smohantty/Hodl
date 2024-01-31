package com.finance.hodl.view.navigation

sealed class HodlNavGraph(val route: String) {
    object Home : HodlNavGraph("home")
    object Bots : HodlNavGraph("bots")
    object Settings : HodlNavGraph("setting")
}

sealed class HodlHome(val route: String) {

    object Test : HodlHome("home_test")
    object Bithumb : HodlHome("home_bithumb")
    object Binance : HodlHome("home_binance")
}

sealed class HodlBots(val route: String) {
    object New : HodlBots("bots_new")
    object Running : HodlBots("bots_running")
    object Stopped : HodlBots("bots_Stopped")
}

sealed class HodlSettings(val route: String) {
    object Main : HodlSettings("settings_main")
}