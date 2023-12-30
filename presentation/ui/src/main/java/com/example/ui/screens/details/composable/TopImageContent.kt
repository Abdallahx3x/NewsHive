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
import com.example.ui.R
import com.example.ui.composable.GradientBackgroundBox
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens

@Composable
fun TopImageContent(
    image: Painter,
    onClickSaveIcon: () -> Unit,
    onClickBackIcon: () -> Unit,
    changeSaveIconColor: Boolean,
) {
    val color = MaterialTheme.customColors
    val dimens = MaterialTheme.dimens

    Box(
        Modifier
            .fillMaxWidth()
            .height(dimens.space420)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimens.space420),
            painter = painterResource(id = R.drawable.empty_image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimens.space420),
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        GradientBackgroundBox(
            gradientStartY = dimens.floatValues.float0,
            gradientEndY = dimens.floatValues.float220
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimens.space24)
                .padding(horizontal = dimens.space16),
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