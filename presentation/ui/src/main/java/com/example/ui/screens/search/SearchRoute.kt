package com.example.ui.screens.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.Screen

fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(Screen.Search.screenRoute) {
        SearchScreen(navController = navController)
    }
}