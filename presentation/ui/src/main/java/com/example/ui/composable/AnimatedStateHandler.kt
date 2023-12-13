package com.example.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ui.R
import com.example.ui.theme.customColors

@Composable
fun AnimatedStateHandler(
    modifier: Modifier = Modifier,
    hasRefreshButton: Boolean = false,
    onRefresh: () -> Unit = {},
    animationResId: Int,
    animationSpeed: Float = 1f
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationResId)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = true,
        speed = animationSpeed
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            progress = { progress })
        AnimatedVisibility(visible = hasRefreshButton) {
            TextButton(onClick = { onRefresh() }) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.refresh),
                    color = MaterialTheme.customColors().onBackground60,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}