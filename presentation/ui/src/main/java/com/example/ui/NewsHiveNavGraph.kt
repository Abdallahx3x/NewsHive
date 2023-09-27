package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.screens.details.detailsRoute
import com.example.ui.screens.discover.discoverRoute
import com.example.ui.screens.home.homeRoute


@Composable
fun NewsHiveNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        homeRoute(navController)
        discoverRoute()
        detailsRoute()
    }
}