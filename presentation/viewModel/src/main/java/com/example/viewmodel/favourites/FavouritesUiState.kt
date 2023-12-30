package com.example.viewmodel.favourites

import com.example.viewmodel.base.BaseUiState

data class FavouritesUiState(
    val favouriteItemsUiState: List<FavouriteItemUiState> = emptyList(),
    val error: String? = null
) : BaseUiState()

data class FavouriteItemUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    override val publishedAt: String = "",
    override val category: String = "",
) : BaseUiState()

fun FavouritesUiState.showContent() = error == null && favouriteItemsUiState.isNotEmpty()
fun FavouritesUiState.showEmpty() = error == null && favouriteItemsUiState.isEmpty()