package com.finance.hodl.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.finance.hodl.view.navigation.HodlNavGraph
import com.finance.hodl.view.navigation.HodlNavHost


@Composable
fun HodlApp() {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val selectedItemIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    navbarList.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex.intValue == index,
                            onClick = {
                                selectedItemIndex.intValue = index
                                navController.navigate(item.destination.route)
                            },
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex.intValue) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                HodlNavHost(navController, HodlNavGraph.Home.route)
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount:Int? = null,
    val destination:HodlNavGraph
)

val navbarList = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Default.Home,
        hasNews = false,
        destination = HodlNavGraph.Home
    ),
    BottomNavigationItem(
        title = "Bot",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Default.Face,
        hasNews = true,
        badgeCount = 5,
        destination = HodlNavGraph.Bots
    ),
    BottomNavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Default.Settings,
        hasNews = true,
        destination = HodlNavGraph.Settings
    )
)



