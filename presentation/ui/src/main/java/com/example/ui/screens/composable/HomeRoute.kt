package com.example.ui.screens.composable

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.homeRoute() {
    composable(BottomNavigationItem.Home.screenRoute) {
        HomeScreen()
    }
}


fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Home.route, builder = builder)
}