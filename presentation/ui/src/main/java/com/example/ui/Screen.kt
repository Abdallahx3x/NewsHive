package com.example.ui

sealed class Screen(val route:String){
    object Home:Screen("Home")
    object Discover:Screen("Discover")
    object Details:Screen("Details")

}
