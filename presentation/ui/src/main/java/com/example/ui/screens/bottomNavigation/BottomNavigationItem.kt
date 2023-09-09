package com.example.ui.screens.bottomNavigation

import com.example.ui.R

sealed class BottomNavigationItem(var title: String, var icon: Int, var screenRoute: String) {
    object Home : BottomNavigationItem(
        title = "Home",
        icon = R.drawable.home,
        screenRoute = "Home"
    )

    object Discover : BottomNavigationItem(
        title = "Discover",
        icon = R.drawable.discover,
        screenRoute = "Discover"
    )

    object Search : BottomNavigationItem(
        title = "Search",
        icon = R.drawable.search,
        screenRoute = "Search"
    )

    object Saved : BottomNavigationItem(
        title = "Saved",
        icon = R.drawable.saved,
        screenRoute = "Saved"
    )
}
