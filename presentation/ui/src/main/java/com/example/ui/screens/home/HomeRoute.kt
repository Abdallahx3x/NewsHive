package com.example.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.Screen
import com.example.ui.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.homeRoute(navController:NavController) {
    composable(BottomNavigationItem.Home.screenRoute) {
        HomeScreen(navController=navController)
    }
}


fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Home.route, builder = builder)
}