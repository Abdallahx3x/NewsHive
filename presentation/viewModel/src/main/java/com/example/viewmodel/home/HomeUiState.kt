package com.example.viewmodel.home



data class HomeUiState(
    val breakingNewsUiState: List<BreakingNewsUiState>?= emptyList(),
    val recommendedNewsUiState: List<RecommendedNewsUiState>?= emptyList(),
    val isLoading:Boolean=false,
    val error:String?=null
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
