package com.example.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.search.composable.SearchResults
import com.example.ui.screens.search.composable.SearchTextField
import com.example.ui.theme.customColors
import com.example.ui.theme.dimens
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.search.SearchInteraction
import com.example.viewmodel.search.SearchUiEffect
import com.example.viewmodel.search.SearchUiState
import com.example.viewmodel.search.SearchViewModel
import com.example.viewmodel.search.showContent
import com.example.viewmodel.search.showError
import com.example.viewmodel.search.showLoading
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    CollectUiEffect(viewModel.effect) { searchUiEffect ->
        when (searchUiEffect) {
            is SearchUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    searchUiEffect.newsItem
                )
            }
        }
    }
    SearchContent(state, viewModel)
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchContent(
    state: SearchUiState,
    searchInteraction: SearchInteraction
) {
    val query = state.query.collectAsState()
    val systemUiController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    val dimens = MaterialTheme.dimens
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.customColors.card,
        darkIcons = !darkMode
    )
    val searchItems = state.searchItems.collectAsLazyPagingItems()
    NewsHiveScaffold(
        hasAppBar = true,
        appBarModifier = Modifier.height(dimens.space88),
        title = {
            SearchTextField(
                onChangeSearchQuery = { searchInteraction.onChangeSearchQuery(it) },
                text = query.value
            )
        },
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
                onRefresh = { searchInteraction.onRefreshData() },
                animationResId = R.raw.no_wifi
            )
        },
        showContent = state.showContent()
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.customColors.background)
                .padding(paddingValues)
        ) {
            key(searchItems.loadState) {
                when {
                    searchItems.loadState.refresh is LoadState.Error && query.value.isNotEmpty() -> {
                        AnimatedStateHandler(
                            hasRefreshButton = true,
                            onRefresh = { searchItems.retry() },
                            animationResId = R.raw.no_wifi
                        )
                    }

                    searchItems.loadState.refresh is LoadState.Loading && query.value.isNotEmpty() -> {
                        AnimatedStateHandler(
                            modifier = Modifier.size(dimens.space200),
                            animationResId = R.raw.loading_animation,
                            animationSpeed = dimens.floatValues.float1_7
                        )
                    }
                }
            }
            when {
                query.value.isEmpty() -> {
                    AnimatedStateHandler(animationResId = R.raw.empty_search)
                    searchInteraction.onChangeSearchQuery("")
                }

                query.value.isNotEmpty() -> {
                    SearchResults(
                        onClickItem = searchInteraction::onClickSearchItem,
                        searchItems = searchItems
                    )
                }
            }
        }
    }
}





