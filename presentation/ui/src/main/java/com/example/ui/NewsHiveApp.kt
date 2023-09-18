package com.example.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.bottomNavigation.BottomNavigation
import com.example.ui.screens.bottomNavigation.currentRoute
import com.example.ui.screens.bottomNavigation.BottomNavigationItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsHiveApp() {
    val navController = rememberNavController()
    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        BottomNavigationItem.Home.screenRoute,
        -> true
        else -> false
    }

    Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {paddingValue->
        Box (modifier = Modifier.padding(paddingValue)){
            NewsHiveNavGraph(navController = navController)
        }
    }

}