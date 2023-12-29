package com.example.ui.screens.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.NewsHiveCard
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens
import com.example.viewmodel.home.RecommendedNewsUiState

@Composable
fun RecommendedNewsSection(
    onNewsItemClick: (RecommendedNewsUiState) -> Unit,
    onSeeAllClick: () -> Unit,
    recommendedNews: List<RecommendedNewsUiState>
) {
    val dimens = MaterialTheme.dimens
    val fontStyle = MaterialTheme.typography
    val color = MaterialTheme.customColors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimens.space16)
            .padding(bottom = dimens.space16),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.recommended), color = color.onBackground87,
            style = fontStyle.titleMedium
        )
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(dimens.space8))
                .clickable { onSeeAllClick() },
            text = stringResource(R.string.view_all),
            color = color.primary,
            style = fontStyle.titleSmall
        )
    }
    LazyColumn(modifier = Modifier.padding(horizontal = dimens.space16)) {
        items(recommendedNews.size) {
            val item = recommendedNews[it]
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(
                            bottom = dimens.space16,
                            start = dimens.space26
                        )
                        .size(dimens.space24)
                        .align(Alignment.CenterStart)
                )
                NewsHiveCard(
                    modifier = Modifier.padding(bottom = dimens.space16),
                    onClick = { onNewsItemClick(item) },
                    painter = rememberAsyncImagePainter(item.imageUrl),
                    category = item.category,
                    title = item.title,
                    date = item.publishedAt
                )
            }
        }
    }

}