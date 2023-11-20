package com.example.ui.screens.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(BottomNavigationItem.Search.screenRoute) {
        SearchScreen(navController = navController)
    }
}