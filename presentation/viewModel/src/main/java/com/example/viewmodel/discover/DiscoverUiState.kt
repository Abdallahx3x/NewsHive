package com.example.viewmodel.discover

import com.example.viewmodel.home.NewsItemUiState

data class DiscoverUiState(
    val pageIndex: Int? = 0,
    val categories: List<String> = listOf("sports", "science", "health", "technology", "business"),
    val sportsNews: List<NewsItemUiState> = emptyList(),
    val scienceNews: List<NewsItemUiState> = emptyList(),
    val healthNews: List<NewsItemUiState> = emptyList(),
    val technologyNews: List<NewsItemUiState> = emptyList(),
    val businessNews: List<NewsItemUiState> = emptyList(),
    val entertainmentNews: List<NewsItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)


data class CategoryNewsUiState(
    val title: String = "",
    val imageUrl: String = "",
    val publishedAt: String = "",
    val categoryName: String = ""
)
