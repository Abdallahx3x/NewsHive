package com.example.ui.screens.favourites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.favouriteRoute(navController: NavController) {
    composable(BottomNavigationItem.Saved.screenRoute) {
        FavouritesScreen(navController = navController)
    }
}