package com.example.ui.screens.viewAll

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.Screen

fun NavGraphBuilder.viewAllRoute(navController: NavController) {
    composable(Screen.ViewAll.screenRoute) {
        ViewAllScreen(navController = navController)
    }
}

fun NavController.navigateToViewAll() {
    navigate(Screen.ViewAll.screenRoute)
}