package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.screens.details.detailsRoute
import com.example.ui.screens.discover.discoverRoute
import com.example.ui.screens.favourites.favouriteRoute
import com.example.ui.screens.home.homeRoute
import com.example.ui.screens.search.searchRoute


@Composable
fun NewsHiveNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        homeRoute(navController)
        discoverRoute(navController)
        favouriteRoute(navController)
        searchRoute(navController)
        detailsRoute(navController)
    }
}