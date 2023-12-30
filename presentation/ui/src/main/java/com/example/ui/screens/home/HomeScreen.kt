package com.example.ui.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.home.composable.BreakingNewsCard
import com.example.ui.screens.home.composable.RecommendedNewsSection
import com.example.ui.screens.viewAll.navigateToViewAll
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens
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
    val color = MaterialTheme.customColors
    val fontStyle = MaterialTheme.typography
    val dimens = MaterialTheme.dimens
    val pageState = rememberPagerState(2)
    val systemUiController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    systemUiController.setSystemBarsColor(
        color = color.card,
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
                modifier = Modifier.size(dimens.space200),
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
                    .padding(top = dimens.space24, start = dimens.space16)
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
                    .padding(top = dimens.space16)
                    .height(dimens.space220),
                contentPadding = PaddingValues(horizontal = dimens.space40),
                pageCount = state.breakingNewsUiState.size,
            ) { page ->
                BreakingNewsCard(
                    modifier = Modifier
                        .size(width = dimens.space340, height = dimens.space200)
                        .graphicsLayer {
                            val pageOffset = (
                                    (pageState.currentPage - page) + pageState
                                        .currentPageOffsetFraction
                                    ).absoluteValue
                            lerp(
                                start = dimens.floatValues.float0_9,
                                stop = dimens.floatValues.float1,
                                fraction = dimens.floatValues.float1 - pageOffset.coerceIn(
                                    dimens.floatValues.float0,
                                    dimens.floatValues.float1
                                )
                            ).also {
                                scaleX = it
                                scaleY = it
                            }
                            alpha = lerp(
                                start = dimens.floatValues.float0_5,
                                stop = dimens.floatValues.float1,
                                fraction = dimens.floatValues.float1 - pageOffset.coerceIn(
                                    dimens.floatValues.float0,
                                    dimens.floatValues.float1
                                )
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
                        targetValue = if (pageState.currentPage == it) dimens.space24 else dimens.space10,
                        label = ""
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = dimens.space4)
                            .padding(bottom = dimens.space24)
                            .size(height = dimens.space10, width = width)
                            .clip(CircleShape)
                            .background(backgroundColor)
                    )
                }
            }
            RecommendedNewsSection(
                onNewsItemClick = homeInteraction::onClickRecommendedNewsItem,
                onSeeAllClick = homeInteraction::onClickViewAll,
                recommendedNews = state.recommendedNewsUiState
            )
        }
    }
}