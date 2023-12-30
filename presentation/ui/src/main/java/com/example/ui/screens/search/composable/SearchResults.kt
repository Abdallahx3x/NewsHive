package com.example.ui.screens.search.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.composable.AnimatedStateHandler
import com.example.ui.composable.NewsHiveCard
import com.example.ui.theme.dimens
import com.example.viewmodel.search.SearchItemUiState

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    onClickItem: (SearchItemUiState) -> Unit,
    searchItems: LazyPagingItems<SearchItemUiState>
) {
    val dimens=MaterialTheme.dimens
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimens.space16),
        contentPadding = PaddingValues(top = dimens.space16)
    ) {
        items(searchItems.itemCount) { card ->
            NewsHiveCard(
                modifier = Modifier.padding(bottom = dimens.space16),
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
                            .height(dimens.space42),
                        animationResId = R.raw.loading_animation
                    )
                }
            }

            searchItems.loadState.append is LoadState.Error -> {
                item {
                    AnimatedStateHandler(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimens.space56),
                        hasRefreshButton = true,
                        onRefresh = { searchItems.retry() },
                        animationResId = R.raw.no_wifi
                    )
                }
            }
        }
    }
}