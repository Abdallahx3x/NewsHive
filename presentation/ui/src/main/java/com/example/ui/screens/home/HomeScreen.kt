package com.example.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ui.R
import com.example.ui.composable.NewsHiveCard
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.home.composable.BreakingNewsCard
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.home.HomeInteraction
import com.example.viewmodel.home.HomeUiEffect
import com.example.viewmodel.home.HomeUiState
import com.example.viewmodel.home.HomeViewModel
import kotlin.math.absoluteValue


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    CollectUiEffect(viewModel.effect) { homeUiEffect ->
        when (homeUiEffect) {
            is HomeUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    homeUiEffect.newsItem
                )
            }

        }
    }
    AnimatedVisibility(visible = state.error != null) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.no_wifi)
        )
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = true
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress })
            TextButton(onClick = { viewModel.getData() }) {
                androidx.compose.material.Text(
                    text = "Refresh", modifier = Modifier,
                    color = color.onBackground60,
                    style = fontStyle.titleMedium
                )
            }
        }

    }
    AnimatedVisibility(visible = state.error == null) {
        HomeContent(state, viewModel)
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    state: HomeUiState,
    homeInteraction: HomeInteraction
) {
    val color = MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography
    val pageState = rememberPagerState(2)
    NewsHiveScaffold(
        hasTitle = true,
        title = stringResource(id = R.string.newshive)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color.background)
        ) {

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

            state.breakingNewsUiState.let {
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
                            title = it[page].title,
                            painter = rememberAsyncImagePainter(it[page].imageUrl),
                            onClick = {
                                homeInteraction.onClickBreakingNewsItem(it[page])
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) {
                    val backgroundColor = if (pageState.currentPage == it) color.primary else color.gray
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
                            .background(backgroundColor)
                    )
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
                items(state.recommendedNewsUiState.size) {
                    val item = state.recommendedNewsUiState[it]
                    Box {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(
                                    bottom = 16.dp,
                                    start = 26.dp
                                )
                                .size(24.dp)
                                .align(CenterStart)
                        )
                        NewsHiveCard(
                            modifier = Modifier.padding(bottom = 16.dp),
                            onClick = { homeInteraction.onClickRecommendedNewsItem(item) },
                            painter = rememberAsyncImagePainter(item.imageUrl),
                            category = item.category,
                            title = item.title,
                            date = item.publishedAt
                        )

                    }

                }
            }

        }

    }

}

@Composable
@Preview()
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
