package com.example.viewmodel.discover

import com.example.viewmodel.base.BaseUiState

data class DiscoverUiState(
    val pageIndex: Int? = 0,
    val categories: List<String> = listOf("sports", "science", "health", "technology", "business"),
    val sportsNews: List<CategoryNewsUiState> = emptyList(),
    val scienceNews: List<CategoryNewsUiState> = emptyList(),
    val healthNews: List<CategoryNewsUiState> = emptyList(),
    val technologyNews: List<CategoryNewsUiState> = emptyList(),
    val businessNews: List<CategoryNewsUiState> = emptyList(),
    val entertainmentNews: List<CategoryNewsUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)


data class CategoryNewsUiState(
    override val title: String,
    override val imageUrl: String,
    override val content: String,
    override val url: String,
    val publishedAt: String = "",
    val categoryName: String = "",
):BaseUiState()
