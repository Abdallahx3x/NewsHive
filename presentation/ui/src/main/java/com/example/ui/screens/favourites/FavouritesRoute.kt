package com.example.ui.screens.favourites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.Screen

fun NavGraphBuilder.favouriteRoute(navController: NavController) {
    composable(Screen.Saved.screenRoute) {
        FavouritesScreen(navController = navController)
    }
}