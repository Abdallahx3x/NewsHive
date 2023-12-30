package com.example.ui.navigation

import com.example.ui.R


sealed class Screen(var icon: Int?=null, var screenRoute: String) {
    object Home : Screen(
        icon = R.drawable.home,
        screenRoute = "Home"
    )

    object Discover : Screen(
        icon = R.drawable.discover,
        screenRoute = "Discover"
    )

    object Search : Screen(
        icon = R.drawable.search,
        screenRoute = "Search"
    )

    object Saved : Screen(
        icon = R.drawable.saved,
        screenRoute = "Saved"
    )
    object Details: Screen(screenRoute="Details")
    object ViewAll: Screen(screenRoute="ViewAll")
}