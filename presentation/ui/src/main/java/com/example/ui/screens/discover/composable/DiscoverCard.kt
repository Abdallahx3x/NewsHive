package com.example.ui.screens.discover.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.customColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverCard(
    painter: Painter,
    contentDescription: String = "",
    category: String,
    title: String,
    date: String,
) {
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography

    Card(
        onClick = {
            Log.i("dsdsesdwwew",painter.toString())

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        colors = CardDefaults.cardColors(color.card)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp), Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(188.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = contentDescription
            )
            Text(
                modifier = Modifier.padding(top = 14.dp).padding(horizontal = 16.dp),
                text = category,
                color = color.onBackground60,
                style = fontStyle.titleSmall
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp), text = title,
                color = color.onBackground87,
                style = fontStyle.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp), text = date,
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
        painter = painterResource(id = R.drawable.moosalah), "testContent",
        "Title", "MmooesttestCategory" +
                "  title:String," +
                "  title:String,  title:String,  title:String,", "testDate"
    )
}

