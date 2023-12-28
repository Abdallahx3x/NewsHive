package com.example.ui.screens.details.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.composable.GradientBackgroundBox
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors

@Composable
fun TopImageContent(
    image: Painter,
    onClickSaveIcon: () -> Unit,
    onClickBackIcon: () -> Unit,
    changeSaveIconColor: Boolean,
) {
    val color = MaterialTheme.customColors()
    Box(
        Modifier
            .fillMaxWidth()
            .height(420.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
            painter = painterResource(id = R.drawable.empty_image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        GradientBackgroundBox(gradientStartY = 0f, gradientEndY = 220f)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onClickBackIcon() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_icon),
                    contentDescription = null,
                    tint = color.onPrimary
                )
            }
            IconButton(onClick = { onClickSaveIcon() }) {
                Icon(
                    painter = painterResource(id = if (changeSaveIconColor) R.drawable.savediconfilled else R.drawable.savedicon),
                    contentDescription = null,
                    tint = color.onPrimary
                )
            }
        }
    }
}

@Composable
@Preview
fun TopImageContentPreview() {
    NewsHiveTheme {
        TopImageContent(
            image = painterResource(id = R.drawable.mosalah),
            onClickSaveIcon = {},
            onClickBackIcon = {},
            changeSaveIconColor = false
        )
    }
}