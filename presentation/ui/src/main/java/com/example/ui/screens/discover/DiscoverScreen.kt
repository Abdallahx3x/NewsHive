package com.example.ui.screens.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.discover.composable.DiscoverResults
import com.example.ui.screens.discover.composable.TabIndicator
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.discover.DiscoverInteraction
import com.example.viewmodel.discover.DiscoverUiEffect
import com.example.viewmodel.discover.DiscoverUiState
import com.example.viewmodel.discover.DiscoverViewModel
import com.example.viewmodel.discover.categoryNews
import com.example.viewmodel.discover.showContent
import com.example.viewmodel.discover.showError
import com.example.viewmodel.discover.showLoading
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    CollectUiEffect(effect = viewModel.effect) { discoverViewModel ->
        when (discoverViewModel) {
            is DiscoverUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    discoverViewModel.newsItem
                )
            }
        }
    }
    DiscoverContent(state, viewModel)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DiscoverContent(
    state: DiscoverUiState,
    discoverInteraction: DiscoverInteraction
) {
    val pageState = rememberPagerState()
    val color = MaterialTheme.customColors
    val dimens = MaterialTheme.dimens
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.customColors.card,
        darkIcons = !darkMode
    )
    NewsHiveScaffold(
        showLoading = state.showLoading(),
        onLoading = {
            AnimatedStateHandler(
                modifier = Modifier.size(dimens.space200),
                animationResId = R.raw.loading_animation,
                animationSpeed = dimens.floatValues.float1_7
            )
        },
        showError = state.showError(),
        onError = {
            AnimatedStateHandler(
                hasRefreshButton = true,
                onRefresh = { discoverInteraction.onRefreshData() },
                animationResId = R.raw.no_wifi
            )
        },
        showContent = state.showContent(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color.background)
        ) {
            ScrollableTabRow(modifier = Modifier
                .fillMaxWidth()
                .height(dimens.space88),
                selectedTabIndex = pageState.currentPage,
                indicator = { tabPosition: List<TabPosition> ->
                    TabIndicator(pageState, tabPosition)
                },
                backgroundColor = color.card,
                divider = { Divider(thickness = dimens.zero, color = color.card) }
            ) {
                state.categoryNews().forEachIndexed { index, category ->
                    Tab(modifier = Modifier
                        .zIndex(6f)
                        .clip(RoundedCornerShape(dimens.intValues.int50)),
                        selected = pageState.currentPage == index,
                        onClick = {
                            scope.launch { pageState.animateScrollToPage(index) }
                        },
                        text = {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (pageState.currentPage == index) color.card else color.onBackground87
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                count = state.categoryNews().size,
                state = pageState
            ) { page ->
                val list = state.categoryNews()[page].list.collectAsLazyPagingItems()
                key(list.loadState) {
                    when {
                        list.loadState.refresh is LoadState.Error -> {
                            AnimatedStateHandler(
                                animationResId = R.raw.no_wifi,
                                hasRefreshButton = true,
                                onRefresh = { list.retry() },
                            )
                        }

                        list.loadState.refresh is LoadState.Loading -> {
                            AnimatedStateHandler(
                                modifier = Modifier.size(dimens.space200),
                                animationResId = R.raw.loading_animation,
                                animationSpeed = dimens.floatValues.float1_7
                            )
                        }
                    }
                }
                AnimatedVisibility(
                    visible = list.loadState.refresh !is LoadState.Error,
                    enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 500))
                ) {
                    DiscoverResults(
                        onClickItem = discoverInteraction::onClickCategoryItem,
                        discoverItems = list
                    )
                }
            }
        }
    }
}
