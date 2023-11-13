package com.example.viewmodel.favourites

import com.example.viewmodel.base.BaseViewModel

sealed interface FavouritesUiEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToDetails(
        val newsItem: String
    ) : FavouritesUiEffect
}