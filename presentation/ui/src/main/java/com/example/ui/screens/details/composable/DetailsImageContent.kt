package com.example.ui.screens.details.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.theme.customColors
import com.example.viewmodel.details.DetailsUiState

@Composable
fun DetailsImageContent(state: DetailsUiState) {
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = rememberAsyncImagePainter(model = state.imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color.transparent,
                        color.black.copy(alpha = 0.8f)
                    ), startY = 200f
                )
            )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(30.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color.black.copy(alpha = 0.6f),
                        color.transparent
                    ), endY = 230f
                )
            )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_icon),
                    contentDescription = null,
                    tint = color.onPrimary
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.saved_icon),
                    contentDescription = null,
                    tint = color.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(bottom = 77.dp)
                .width(320.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp))
        ) {
            Image(
                modifier = Modifier
                    .matchParentSize()
                    .blur(24.dp),
                painter = rememberAsyncImagePainter(model = state.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                text = state.title,
                style = fontStyle.titleLarge,
                color = color.onPrimary
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Swipe to see more",
            style = fontStyle.titleMedium,
            color = color.onPrimary
        )
        Icon(
            modifier = Modifier.padding(bottom = 16.dp),
            painter = painterResource(id = R.drawable.alt_arrow_down),
            contentDescription = null,
            tint = color.onPrimary
        )
    }
}
