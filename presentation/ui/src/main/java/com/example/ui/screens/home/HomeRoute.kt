package com.example.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.Screen

fun NavGraphBuilder.homeRoute(navController:NavController) {
    composable(Screen.Home.screenRoute) {
        HomeScreen(navController=navController)
    }
}