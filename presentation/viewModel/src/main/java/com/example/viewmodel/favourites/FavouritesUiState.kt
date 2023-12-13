package com.example.viewmodel.favourites

import com.example.viewmodel.base.BaseUiState

data class FavouritesUiState(
    val favouritesItemUiState: List<FavouritesItemUiState> = emptyList(),
    val error: String? = null
) : BaseUiState()

data class FavouritesItemUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    override val publishedAt: String = "",
    val category: String = "",
) : BaseUiState()

fun FavouritesUiState.showContent() = error == null && favouritesItemUiState.isNotEmpty()
fun FavouritesUiState.showEmpty() = error == null && favouritesItemUiState.isEmpty()