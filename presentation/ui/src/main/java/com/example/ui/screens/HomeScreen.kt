package com.example.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ui.composable.NewsHiveCard
import com.example.ui.screens.composable.BreakingNewsCard
import com.example.ui.theme.customColors
import com.example.viewmodel.HomeUiState
import com.example.viewmodel.HomeViewModel
import kotlin.math.absoluteValue


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    HomeContent(state)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    state: HomeUiState
) {
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    val pageState = rememberPagerState(2)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.background)
    ) {

        Text(
            text = "News Hive", modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 12.dp),
            style = fontStyle.titleLarge, color = color.primary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Breaking News",
                style = fontStyle.titleMedium,
                color = color.onBackground87
            )
        }

        state.breakingNewsUiState?.let {
            HorizontalPager(
                state = pageState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(250.dp),
                contentPadding = PaddingValues(horizontal = 40.dp),
                pageCount = it.size,
            ) { page ->
                Card(
                    Modifier
                        .height(200.dp)
                        .width(280.dp)
                        .padding(top = 28.dp)
                        // .clickable {}
                        .scale(scaleY = 1.2f, scaleX = 1.4f)
                        .graphicsLayer {
                            val pageOffset = (
                                    (pageState.currentPage - page) + pageState
                                        .currentPageOffsetFraction
                                    ).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also {
                                scaleX = it
                                scaleY = it
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    colors = CardDefaults.cardColors(color.transparent),
                )
                {
                    BreakingNewsCard(
                        it[page].title,
                        rememberAsyncImagePainter(it[page].imageUrl)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) {
                val color = if (pageState.currentPage == it) color.primary else color.gray
                val width: Dp by animateDpAsState(
                    targetValue = if (pageState.currentPage == it) 25.dp else 10.dp,
                    label = ""
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 24.dp)
                        .size(height = 10.dp, width = width)
                        .clip(CircleShape)
                        .background(color)
                ) {

                }
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recommended", color = color.onBackground87,
                style = fontStyle.titleMedium
            )
            Text(
                text = "View All", modifier = Modifier.clickable { }, color.primary,
                style = fontStyle.titleSmall
            )
        }
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(state.recommendedNewsUiState!!.size) {
                val item = state.recommendedNewsUiState!![it]
                NewsHiveCard(
                    modifier = Modifier.padding(bottom = 16.dp),
                    painter = rememberAsyncImagePainter(item.imageUrl),
                    category = item.category!!,
                    title = item.title!!,
                    date = item.publishedAt!!
                )
            }
        }
    }
}


@Composable
@Preview()
fun HomeScreenPreview() {
    HomeScreen()
}
