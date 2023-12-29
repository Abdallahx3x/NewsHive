package com.example.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens

@Composable
fun GradientBackgroundBox(
    modifier: Modifier = Modifier,
    gradientStartY: Float,
    gradientEndY: Float,
) {
    val color = MaterialTheme.customColors
    val dimens = MaterialTheme.dimens

    Box(
        modifier = modifier
            .fillMaxSize()
            .blur(dimens.space30)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color.black.copy(alpha = dimens.floatValues.float0_6),
                        color.transparent
                    ),
                    endY = gradientEndY
                )
            )
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color.transparent, color.black.copy(alpha = dimens.floatValues.float0_8)
                    ),
                    startY = gradientStartY
                )
            )
    )
}