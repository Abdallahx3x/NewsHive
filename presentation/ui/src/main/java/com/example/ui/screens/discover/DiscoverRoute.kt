package com.example.ui.screens.discover

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.Screen

fun NavGraphBuilder.discoverRoute(navController: NavController){
    composable(Screen.Discover.screenRoute){
        DiscoverScreen(navController = navController)
    }
}