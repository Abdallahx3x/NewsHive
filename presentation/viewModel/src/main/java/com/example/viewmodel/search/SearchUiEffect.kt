package com.example.viewmodel.search

import com.example.viewmodel.base.BaseViewModel

sealed interface SearchUiEffect:BaseViewModel.BaseUiEffect {
    data class NavigateToDetails(
        val newsItem: String
    ) : SearchUiEffect
}