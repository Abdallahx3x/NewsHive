package com.example.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.R
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHiveCard(
    modifier: Modifier,
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String = "",
    category: String,
    title: String,
    date: String
) {
    val color = MaterialTheme.customColors
    val fontStyle = MaterialTheme.typography
    val dimens = MaterialTheme.dimens

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(dimens.space90),
        colors = CardDefaults.cardColors(color.card),
        shape = RoundedCornerShape(dimens.space12),
        onClick = { onClick() }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(dimens.space90)
                        .clip(RoundedCornerShape(dimens.space12)),
                    painter = painterResource(id = R.drawable.empty_image),
                    contentScale = ContentScale.Crop, contentDescription = contentDescription
                )
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(dimens.space90)
                        .clip(RoundedCornerShape(dimens.space12)),
                    painter = painter,
                    contentScale = ContentScale.Crop, contentDescription = contentDescription
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = dimens.space24),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    modifier = Modifier, text = category,
                    color = color.onBackground60,
                    style = fontStyle.titleSmall
                )
                Text(
                    modifier = Modifier, text = title,
                    color = color.onBackground87,
                    style = fontStyle.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier, text = date,
                    color = color.onBackground60,
                    style = fontStyle.titleSmall
                )
            }
        }
    }
}

@Composable
@Preview
fun NewsHiveCardPreview() {
    NewsHiveTheme {
        NewsHiveCard(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.moosalah),
            onClick = {},
            category = "test Category",
            title = "Title",
            contentDescription = "Mmooesttest Mmooesttest Description ",
            date = "testDate"
        )
    }
}

