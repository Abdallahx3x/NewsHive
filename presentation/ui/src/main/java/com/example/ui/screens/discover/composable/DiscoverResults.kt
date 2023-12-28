package com.example.ui.screens.discover.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.viewmodel.discover.CategoryNewsUiState

@Composable
fun DiscoverResults(
    modifier: Modifier = Modifier,
    onClickItem: (CategoryNewsUiState) -> Unit,
    discoverItems: LazyPagingItems<CategoryNewsUiState>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(discoverItems.itemCount) { item ->
            DiscoverCard(
                painter = rememberAsyncImagePainter(model = discoverItems[item]!!.imageUrl),
                onClick = { onClickItem(discoverItems[item]!!) },
                category = discoverItems[item]!!.categoryName,
                title = discoverItems[item]!!.title,
                date = discoverItems[item]!!.publishedAt,
            )
        }
        when {
            discoverItems.loadState.append is LoadState.Loading -> {
                item {
                    AnimatedStateHandler(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp),
                        animationResId = R.raw.loading_animation
                    )
                }
            }

            discoverItems.loadState.append is LoadState.Error -> {
                item {
                    AnimatedStateHandler(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        hasRefreshButton = true,
                        onRefresh = { discoverItems.retry() },
                        animationResId = R.raw.no_wifi
                    )
                }
            }
        }
    }

}