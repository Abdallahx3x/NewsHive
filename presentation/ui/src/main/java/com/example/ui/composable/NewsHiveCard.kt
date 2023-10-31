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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.NewsHiveTheme
import com.example.ui.theme.customColors


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
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp),
        colors = CardDefaults.cardColors(color.card),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick() }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(90.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    painter = painter,
                    contentScale = ContentScale.Crop, contentDescription = contentDescription
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 24.dp),
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

