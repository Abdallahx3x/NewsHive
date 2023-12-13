package com.example.viewmodel.discover

import com.example.viewmodel.base.BaseUiState

data class DiscoverUiState(
    val categories: List<String> = listOf("sports", "technology", "health", "science", "business"),
    val sportsNews: List<CategoryNewsUiState> = emptyList(),
    val scienceNews: List<CategoryNewsUiState> = emptyList(),
    val healthNews: List<CategoryNewsUiState> = emptyList(),
    val technologyNews: List<CategoryNewsUiState> = emptyList(),
    val businessNews: List<CategoryNewsUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CategoryNewsUiState(
    override val title: String,
    override val imageUrl: String,
    override val content: String,
    override val url: String,
    override val publishedAt: String = "",
    val categoryName: String = ""
) : BaseUiState()

data class Category(
    val name: String,
    val list: List<CategoryNewsUiState>
)

fun DiscoverUiState.categoryNews() = mutableListOf(
    Category("Sports", sportsNews),
    Category("Technology", technologyNews),
    Category("Health", healthNews),
    Category("Science", scienceNews),
    Category("Business", businessNews),
)

fun DiscoverUiState.showError() = !isLoading && error != null
fun DiscoverUiState.showLoading() = isLoading && error == null
fun DiscoverUiState.showContent() = !isLoading && error == null
