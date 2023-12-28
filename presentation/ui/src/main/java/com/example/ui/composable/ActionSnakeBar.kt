package com.example.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.DarkCard
import com.example.ui.theme.LightCard
import kotlinx.coroutines.delay

@Composable
fun ActionSnakeBar(
    contentMessage: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
) {
    val textStyle = MaterialTheme.typography
    var timeVisible by remember { mutableStateOf(isVisible) }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            timeVisible = true
            delay(2000)
            timeVisible = false
        } else {
            timeVisible = false
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            visible = timeVisible
        ) {
            Snackbar(shape = RoundedCornerShape(12.dp), containerColor = DarkCard) {
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(3f),
                        text = contentMessage,
                        style = textStyle.bodyMedium,
                        color = LightCard
                    )
                }
            }
        }
    }
}