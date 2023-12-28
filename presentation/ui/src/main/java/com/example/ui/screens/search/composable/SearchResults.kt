package com.example.ui.screens.search.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveCard
import com.example.viewmodel.search.SearchItemUiState

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    onClickItem: (SearchItemUiState) -> Unit,
    searchItems: LazyPagingItems<SearchItemUiState>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp)
    ) {
        items(searchItems.itemCount) { card ->
            NewsHiveCard(
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = { onClickItem(searchItems[card]!!) },
                painter = rememberAsyncImagePainter(searchItems[card]!!.imageUrl),
                category = searchItems[card]!!.category,
                title = searchItems[card]!!.title,
                date = searchItems[card]!!.publishedAt
            )
        }
        when {
            searchItems.loadState.append is LoadState.Loading -> {
                item {
                    AnimatedStateHandler(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp),
                        animationResId = R.raw.loading_animation
                    )
                }
            }

            searchItems.loadState.append is LoadState.Error -> {
                item {
                    AnimatedStateHandler(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        hasRefreshButton = true,
                        onRefresh = { searchItems.retry() },
                        animationResId = R.raw.no_wifi
                    )
                }
            }
        }
    }
}