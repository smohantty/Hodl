package com.finance.hodl.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.finance.hodl.R
import com.finance.hodl.designsystem.icon.HodlIcons

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class HodlTopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = HodlIcons.Home,
        unselectedIcon = HodlIcons.HomeBorder,
        iconTextId = R.string.home,
        titleTextId = R.string.app_name,
    ),
    BOTS(
        selectedIcon = HodlIcons.Bots,
        unselectedIcon = HodlIcons.BotsBorder,
        iconTextId = R.string.bots,
        titleTextId = R.string.bots,
    ),
    SETTINGS(
        selectedIcon = HodlIcons.Settings,
        unselectedIcon = HodlIcons.SettingsBorder,
        iconTextId = R.string.settings,
        titleTextId = R.string.settings,
    ),
}