package com.example.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ui.theme.NewsHiveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var keepSplashOpened = true
        installSplashScreen().setKeepOnScreenCondition { keepSplashOpened }
        setContent {
            NewsHiveTheme {
                NewsHiveApp(onDataLoaded = { keepSplashOpened = false })
            }
        }
    }
}
