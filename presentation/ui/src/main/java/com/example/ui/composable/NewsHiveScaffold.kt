package com.example.ui.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.customColors


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsHiveScaffold(
    hasAppBar: Boolean = false,
    @SuppressLint("ModifierParameter") appBarModifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    titleContentColor: Color = MaterialTheme.customColors.primary,
    appBarContainerColor: Color = MaterialTheme.customColors.card,
    navigationIcon: @Composable () -> Unit = {},
    showLoading: Boolean = false,
    onLoading: @Composable () -> Unit = {},
    showError: Boolean = false,
    onError: @Composable () -> Unit = {},
    showEmpty: Boolean = false,
    onEmpty: @Composable () -> Unit = {},
    showContent: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AnimatedVisibility(visible = hasAppBar) {
                NewsHiveTopAppBar(
                    modifier = appBarModifier,
                    title = { title() },
                    navigationIcon = { navigationIcon() },
                    containerColor = appBarContainerColor,
                    titleContentColor = titleContentColor
                )
            }
        }
    ) { paddingValues ->
        AnimatedVisibility(
            visible = showError,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) { onError() }
        AnimatedVisibility(
            visible = showLoading,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) { onLoading() }
        AnimatedVisibility(
            visible = showEmpty,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) { onEmpty() }
        AnimatedVisibility(
            visible = showContent,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) { content(paddingValues) }
    }
}


