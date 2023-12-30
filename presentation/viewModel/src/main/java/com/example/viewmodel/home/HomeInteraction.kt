package com.example.viewmodel.home

interface HomeInteraction {
    fun onClickViewAll()
    fun onClickBreakingNewsItem(breakingNewsUiState: BreakingNewsUiState)
    fun onClickRecommendedNewsItem(recommendedNewsUiState: RecommendedNewsUiState)
    fun onRefreshData()
}