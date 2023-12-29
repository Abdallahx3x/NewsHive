package com.example.ui.screens.discover.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.ui.R
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverCard(
    painter: Painter,
    onClick: () -> Unit,
    contentDescription: String = "",
    category: String,
    title: String,
    date: String,
) {
    val color = MaterialTheme.customColors
    val fontStyle = MaterialTheme.typography
    val dimens = MaterialTheme.dimens

    Card(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(dimens.space320)
            .padding(horizontal = dimens.space16)
            .padding(top = dimens.space16),
        colors = CardDefaults.cardColors(color.card)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = dimens.space16), Arrangement.SpaceBetween
        ) {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(dimens.space24)
                        .align(Alignment.Center)
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimens.space188)
                        .clip(RoundedCornerShape(dimens.space12)),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = contentDescription
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = dimens.space14)
                    .padding(horizontal = dimens.space16),
                text = category,
                color = color.onBackground60,
                style = fontStyle.titleSmall
            )
            Text(
                modifier = Modifier.padding(horizontal = dimens.space16),
                text = title,
                color = color.onBackground87,
                style = fontStyle.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(horizontal = dimens.space16),
                text = date,
                color = color.onBackground60,
                style = fontStyle.titleSmall
            )
        }
    }
}

@Composable
@Preview
fun DiscoverCardPreview() {
    DiscoverCard(
        painter = painterResource(id = R.drawable.moosalah), {}, "testContent",
        "Title", "MmooesttestCategory" +
                "  title:String," +
                "  title:String,  title:String,  title:String,", "testDate"
    )
}

