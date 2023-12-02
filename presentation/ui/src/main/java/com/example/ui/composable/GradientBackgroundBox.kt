package com.example.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.ui.theme.customColors

@Composable
fun GradientBackgroundBox(
    modifier: Modifier = Modifier,
    gradientStartY: Float,
    gradientEndY: Float,
) {
    val color = MaterialTheme.customColors()

    Box(
        modifier = modifier
            .fillMaxSize()
            .blur(30.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(color.black.copy(alpha = 0.6f), color.transparent),
                    endY = gradientEndY
                )
            )
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(color.transparent, color.black.copy(alpha = 0.8f)),
                    startY = gradientStartY
                )
            )
    )
}