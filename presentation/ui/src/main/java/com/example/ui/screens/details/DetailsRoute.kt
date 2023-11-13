package com.example.ui.screens.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ui.Screen
import com.example.viewmodel.details.DetailsArgs

fun NavGraphBuilder.detailsRoute(navController: NavController) {
    composable(
        route = "${Screen.Details.route}/{${DetailsArgs.NEWS_ITEM}}",
        arguments = listOf(
            navArgument(DetailsArgs.NEWS_ITEM) { NavType.StringType }
        )
    ) {
        DetailsScreen(navController = navController)
    }

}

fun NavController.navigateToDetails(
    newsItem: String
) {
    navigate("${Screen.Details.route}/$newsItem")
}