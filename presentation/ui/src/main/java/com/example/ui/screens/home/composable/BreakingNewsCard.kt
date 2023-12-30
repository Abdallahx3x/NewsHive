package com.example.ui.screens.home.composable

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreakingNewsCard(
    modifier: Modifier,
    title: String,
    painter: Painter,
    onClick: () -> Unit,
    contentDescription: String = ""
) {
    val colors = MaterialTheme.customColors
    val fontStyle = MaterialTheme.typography
    val dimens = MaterialTheme.dimens

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.space24),
        elevation = CardDefaults.cardElevation(dimens.space6),
        onClick = { onClick() }
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.empty_image),
                contentScale = ContentScale.Crop,
                contentDescription = contentDescription
            )
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = contentDescription
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                colors.transparent,
                                colors.black.copy(alpha = dimens.floatValues.float0_8)
                            ), startY = dimens.floatValues.float200
                        )
                    )
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = dimens.space12)
                    .padding(horizontal = dimens.space16),
                text = title,
                color = Color.White,
                style = fontStyle.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun BreakingNewsCardPreview() {
    BreakingNewsCard(
        Modifier,
        "moo salah score a wonderful goal", rememberAsyncImagePainter(
            model = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg"
        ), {}
    )
}