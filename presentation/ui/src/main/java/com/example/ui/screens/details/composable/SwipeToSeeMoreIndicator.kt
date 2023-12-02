package com.example.ui.screens.details.composable

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.NewsHiveTheme

@Composable
fun SwipeToSeeMoreIndicator(color: Color, fontStyle: TextStyle) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val position by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse), label = ""
    )
    Column {
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.swipe_to_see_more),
            style = fontStyle,
            color = color
        )
        Icon(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = position.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.alt_arrow_down),
            contentDescription = null,
            tint = color
        )
    }
}

@Composable
@Preview
fun SwipeToSeeMoreIndicatorPreview() {
    NewsHiveTheme {
        SwipeToSeeMoreIndicator(
            color = MaterialTheme.colorScheme.onPrimary,
            fontStyle = MaterialTheme.typography.titleMedium
        )
    }
}