package com.example.ui.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsHiveScaffold(
    hasTitle: Boolean=false,
    title: String="",
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AnimatedVisibility(visible = hasTitle) {
                NewsHiveTopAppBar(title = title)
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}