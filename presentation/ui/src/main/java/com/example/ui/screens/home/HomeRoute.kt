package com.example.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.homeRoute(navController:NavController) {
    composable(BottomNavigationItem.Home.screenRoute) {
        HomeScreen(navController=navController)
    }
}