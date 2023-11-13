package com.example.viewmodel.favourites

import com.example.viewmodel.base.BaseUiState

data class FavouritesUiState(
    val favouritesItemUiState:List<FavouritesItemUiState> = emptyList(),
    val empty:Boolean=true,
    val error: String? = null
) : BaseUiState()

data class FavouritesItemUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    val category: String = "",
    val publishedAt: String = ""
) : BaseUiState()

