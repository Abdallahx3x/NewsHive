package com.example.ui.screens.viewAll

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveCard
import com.example.ui.composable.NewsHiveScaffold
import com.example.ui.screens.details.navigateToDetails
import com.example.ui.theme.customColors
import com.example.ui.util.CollectUiEffect
import com.example.viewmodel.viewAll.ViewAllInteraction
import com.example.viewmodel.viewAll.ViewAllUiEffect
import com.example.viewmodel.viewAll.ViewAllUiState
import com.example.viewmodel.viewAll.ViewAllViewModel
import com.example.viewmodel.viewAll.showContent
import com.example.viewmodel.viewAll.showError
import com.example.viewmodel.viewAll.showLoading
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ViewAllScreen(
    viewModel: ViewAllViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    CollectUiEffect(effect = viewModel.effect) { viewAllViewModel ->
        when (viewAllViewModel) {
            is ViewAllUiEffect.NavigateToDetails -> {
                navController.navigateToDetails(
                    viewAllViewModel.newsItem
                )
            }

            is ViewAllUiEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }
    ViewAllContent(state, viewModel)

}

@Composable
fun ViewAllContent(state: ViewAllUiState, viewAllInteraction: ViewAllInteraction) {
    val color = MaterialTheme.customColors()
    val systemUiController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    val viewAllList = state.viewAllItemsUiState.collectAsLazyPagingItems()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.customColors().card,
        darkIcons = !darkMode
    )
    NewsHiveScaffold(
        hasAppBar = true,
        title = {
            Row (modifier = Modifier.fillMaxWidth().padding(start = 24.dp)){
                Text(
                    text = stringResource(id = R.string.recommended),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge,
                    color = color.onBackground87
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { viewAllInteraction.onClickBackButton() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_icon),
                    contentDescription = null,
                    tint = color.onBackground87
                )
            }
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
                onRefresh = { viewAllInteraction.onRefreshData() },
                animationResId = R.raw.no_wifi
            )
        },
        showContent = state.showContent(),
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().background(color.background).padding(paddingValues)
        ) {
            key(viewAllList.loadState) {
                when {
                    viewAllList.loadState.refresh is LoadState.Error -> {
                        AnimatedStateHandler(
                            hasRefreshButton = true,
                            onRefresh = {viewAllInteraction.onRefreshData() },
                            animationResId = R.raw.no_wifi
                        )
                    }

                    viewAllList.loadState.refresh is LoadState.Loading -> {
                        AnimatedStateHandler(
                            modifier = Modifier.size(200.dp),
                            animationResId = R.raw.loading_animation,
                            animationSpeed = 1.7f
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(viewAllList.itemCount) { item ->
                    NewsHiveCard(
                        modifier = Modifier.padding(bottom = 16.dp),
                        onClick = { viewAllInteraction.onClickViewAllItem(viewAllList[item]!!) },
                        painter = rememberAsyncImagePainter(viewAllList[item]!!.imageUrl),
                        category = viewAllList[item]!!.category,
                        title = viewAllList[item]!!.title,
                        date = viewAllList[item]!!.publishedAt
                    )
                }
                when {
                    viewAllList.loadState.append is LoadState.Loading -> {
                        item {
                            AnimatedStateHandler(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp),
                                animationResId = R.raw.loading_animation
                            )
                        }
                    }

                    viewAllList.loadState.append is LoadState.Error -> {
                        item {
                            AnimatedStateHandler(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                hasRefreshButton = true,
                                onRefresh = { viewAllList.retry() },
                                animationResId = R.raw.no_wifi
                            )
                        }
                    }
                }
            }
        }
    }
}