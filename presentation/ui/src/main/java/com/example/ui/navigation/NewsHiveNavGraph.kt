package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.screens.details.detailsRoute
import com.example.ui.screens.discover.discoverRoute
import com.example.ui.screens.favourites.favouriteRoute
import com.example.ui.screens.home.homeRoute
import com.example.ui.screens.search.searchRoute
import com.example.ui.screens.viewAll.viewAllRoute


@Composable
fun NewsHiveNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.screenRoute) {
        homeRoute(navController)
        discoverRoute(navController)
        favouriteRoute(navController)
        searchRoute(navController)
        detailsRoute(navController)
        viewAllRoute(navController)
    }
}