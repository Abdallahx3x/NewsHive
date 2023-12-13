package com.example.ui.screens.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.discover.composable.DiscoverCard
import com.example.ui.screens.discover.composable.TabIndicator
import com.example.ui.theme.customColors
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
    val color = MaterialTheme.customColors()
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.customColors().card,
        darkIcons = !darkMode
    )
    NewsHiveScaffold(
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
                .height(88.dp),
                selectedTabIndex = pageState.currentPage,
                indicator = { tabPosition: List<TabPosition> ->
                    TabIndicator(pageState, tabPosition)
                },
                backgroundColor = color.card,
                divider = { Divider(thickness = 0.dp, color = color.card) }
            ) {
                state.categoryNews().forEachIndexed { index, category ->
                    Tab(modifier = Modifier
                        .zIndex(6f)
                        .clip(RoundedCornerShape(50)),
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(state.categoryNews()[page].list) { item ->
                        DiscoverCard(
                            painter = rememberAsyncImagePainter(model = item.imageUrl),
                            onClick = { discoverInteraction.onClickCategoryItem(item) },
                            category = item.categoryName,
                            title = item.title,
                            date = item.publishedAt,
                        )
                    }
                }
            }
        }
    }
}