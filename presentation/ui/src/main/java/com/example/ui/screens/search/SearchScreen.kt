package com.example.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveCard
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.screens.search.composable.SearchTextField
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.search.SearchInteraction
import com.example.viewmodel.search.SearchUiEffect
import com.example.viewmodel.search.SearchUiState
import com.example.viewmodel.search.SearchViewModel
import com.example.viewmodel.search.showContent
import com.example.viewmodel.search.showEmpty
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
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.customColors().card,
        darkIcons = !darkMode
    )
    NewsHiveScaffold(
        hasAppBar = true,
        appBarModifier = Modifier.height(88.dp),
        title = {
            SearchTextField(
                onChangeSearchQuery = { searchInteraction.onChangeSearchQuery(it) },
                text = query.value
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
        showEmpty = state.showEmpty(),
        onEmpty = {
            AnimatedStateHandler(
                modifier = Modifier.size(300.dp),
                animationResId = R.raw.empty_search,
                animationSpeed = 1.5f
            )
        },
        onError = {
            AnimatedStateHandler(
                hasRefreshButton = true,
                onRefresh = { searchInteraction.onRefreshData() },
                animationResId = R.raw.no_wifi
            )
        },
        showContent = state.showContent()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.customColors().background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(state.searchItems) { card ->
                Box {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(
                                bottom = 16.dp,
                                start = 26.dp
                            )
                            .size(24.dp)
                            .align(Alignment.CenterStart)
                    )
                    NewsHiveCard(
                        modifier = Modifier.padding(bottom = 16.dp),
                        onClick = {
                            searchInteraction.onClickSearchItem(card)
                        },
                        painter = rememberAsyncImagePainter(card.imageUrl),
                        category = card.category,
                        title = card.title,
                        date = card.publishedAt
                    )
                }
            }
        }
    }
}

