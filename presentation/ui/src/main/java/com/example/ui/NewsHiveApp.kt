package com.example.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.bottomNavigation.BottomNavigation
import com.example.ui.screens.bottomNavigation.BottomNavigationItem
import com.example.ui.screens.bottomNavigation.currentRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsHiveApp() {
    val navController = rememberNavController()
    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        BottomNavigationItem.Home.screenRoute,
        BottomNavigationItem.Discover.screenRoute
        -> true

        else -> false
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.White, darkIcons = true)

    Scaffold(bottomBar = { if (shouldShowBottomNavigation) BottomNavigation(navController = navController) }) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            NewsHiveNavGraph(navController = navController)
        }
    }

}

