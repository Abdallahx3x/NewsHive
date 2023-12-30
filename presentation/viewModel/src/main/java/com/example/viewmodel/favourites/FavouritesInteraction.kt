package com.example.viewmodel.favourites

interface FavouritesInteraction {
    fun onClickFavouriteItem(favouritesItemUiState: FavouriteItemUiState)
    fun onDismissNews(title: String)
}