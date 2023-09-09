package com.example.viewmodel



data class HomeUiState(
    val breakingNewsUiState: List<BreakingNewsUiState>?= emptyList(),
    val recommendedNewsUiState: List<RecommendedNewsUiState>?= emptyList()
)
data class BreakingNewsUiState(
    val title:String?="",
    val imageUrl:String?="",
    val category: String?=""
)

data class RecommendedNewsUiState(
    val title:String?="",
    val imageUrl:String?="",
    val publishedAt:String?="",
    val category: String?=""
)
