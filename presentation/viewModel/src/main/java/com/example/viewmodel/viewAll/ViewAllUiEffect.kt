package com.example.viewmodel.viewAll

import com.example.viewmodel.base.BaseViewModel

interface ViewAllUiEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToDetails(
        val newsItem: String
    ) : ViewAllUiEffect
    object NavigateBack : ViewAllUiEffect
}