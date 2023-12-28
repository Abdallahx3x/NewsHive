package com.example.ui.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveCard
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.home.composable.BreakingNewsCard
import com.example.ui.screens.viewAll.navigateToViewAll
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.home.HomeInteraction
import com.example.viewmodel.home.HomeUiEffect
import com.example.viewmodel.home.HomeUiState
import com.example.viewmodel.home.HomeViewModel
import com.example.viewmodel.home.showContent
import com.example.viewmodel.home.showError
import com.example.viewmodel.home.showLoading
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.absoluteValue


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    CollectUiEffect(viewModel.effect) { homeUiEffect ->
        when (homeUiEffect) {
            is HomeUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    homeUiEffect.newsItem
                )
            }

            is HomeUiEffect.NavigateToViewALl -> {
                navController.navigateToViewAll()
            }
        }
    }
    HomeContent(state, viewModel)
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
    val systemUiController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.customColors().card,
        darkIcons = !darkMode
    )
    NewsHiveScaffold(
        hasAppBar = true,
        title = {
            Text(
                text = stringResource(id = R.string.newshive),
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
        },
        showLoading = state.showLoading(),
        onLoading = {
            AnimatedStateHandler(
                modifier = Modifier.size(200.dp),
                animationResId = R.raw.loading_animation,
                animationSpeed = 1.7f
            )
        },
        showError = state.showError(),
        onError = {
            AnimatedStateHandler(
                hasRefreshButton = true,
                onRefresh = { homeInteraction.onRefreshData() },
                animationResId = R.raw.no_wifi
            )
        },
        showContent = state.showContent()
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
                    .padding(top = 24.dp, start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.breaking_news),
                    style = fontStyle.titleMedium,
                    color = color.onBackground87
                )
            }
            HorizontalPager(
                state = pageState,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(220.dp),
                contentPadding = PaddingValues(horizontal = 40.dp),
                pageCount = state.breakingNewsUiState.size,
            ) { page ->
                BreakingNewsCard(
                    modifier = Modifier
                        .size(width = 340.dp, height = 200.dp)
                        .graphicsLayer {
                            val pageOffset = (
                                    (pageState.currentPage - page) + pageState
                                        .currentPageOffsetFraction
                                    ).absoluteValue
                            lerp(
                                start = 0.9f,
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
                    title = state.breakingNewsUiState[page].title,
                    painter = rememberAsyncImagePainter(state.breakingNewsUiState[page].imageUrl),
                    onClick = {
                        homeInteraction.onClickBreakingNewsItem(state.breakingNewsUiState[page])
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                repeat(state.breakingNewsUiState.size) {
                    val backgroundColor =
                        if (pageState.currentPage == it) color.primary else color.gray
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
                    text = stringResource(R.string.recommended), color = color.onBackground87,
                    style = fontStyle.titleMedium
                )
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { homeInteraction.onClickViewAll() },
                    text = stringResource(R.string.view_all),
                    color = color.primary,
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