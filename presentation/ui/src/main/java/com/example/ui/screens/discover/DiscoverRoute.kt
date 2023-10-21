package com.example.ui.screens.discover

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen
import com.example.ui.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.discoverRoute(navController: NavController){
    composable(BottomNavigationItem.Discover.screenRoute){
        DiscoverScreen(navController = navController)
    }
}
fun NavController.navigateToDiscover(builder:NavOptionsBuilder.()-> Unit={}){
    navigate(Screen.Discover.route)

}