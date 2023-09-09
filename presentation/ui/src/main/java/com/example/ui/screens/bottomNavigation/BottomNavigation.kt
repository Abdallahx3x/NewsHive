package com.example.ui.screens.bottomNavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ui.theme.customColors


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Discover,
        BottomNavigationItem.Search,
        BottomNavigationItem.Saved,
    )

    val color = MaterialTheme.customColors()
    NavigationBar(containerColor = color.card) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute(navController = navController) == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = color.onPrimary,
                    selectedTextColor = color.primary,
                    indicatorColor = color.primary,
                    unselectedIconColor = color.onBackground60,
                    unselectedTextColor = color.onBackground60
                ),
                alwaysShowLabel = false,
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                })
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}