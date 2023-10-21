package com.example.viewmodel.discover

import com.example.viewmodel.base.BaseViewModel

sealed interface DiscoverUiEffect: BaseViewModel.BaseUiEffect {
    data class NavigateToDetails(
        val newsItem: String
    ) : DiscoverUiEffect
}