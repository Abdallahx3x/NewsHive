package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ui.navigation.BottomNavigation
import com.example.ui.navigation.NewsHiveNavGraph
import com.example.ui.navigation.Screen
import com.example.ui.navigation.currentRoute
import kotlinx.coroutines.delay


@Composable
fun NewsHiveApp(onDataLoaded: () -> Unit) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        delay(100)
        onDataLoaded()
    }
    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        Screen.Home.screenRoute,
        Screen.Discover.screenRoute,
        Screen.Saved.screenRoute,
        Screen.Search.screenRoute
        -> true

        else -> false
    }

    Scaffold(bottomBar = { if (shouldShowBottomNavigation) BottomNavigation(navController = navController) }) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            NewsHiveNavGraph(navController = navController)
        }
    }
}

