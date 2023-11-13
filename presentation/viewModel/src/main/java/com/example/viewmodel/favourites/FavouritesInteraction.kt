package com.example.viewmodel.favourites

interface FavouritesInteraction {
    fun onClickFavouriteItem(favouritesItemUiState: FavouritesItemUiState)
    fun onDismissNews(title: String)
}