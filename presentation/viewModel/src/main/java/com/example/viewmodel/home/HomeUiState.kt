package com.example.viewmodel.home



data class HomeUiState(
    val breakingNewsUiState: List<NewsUiState>?= emptyList(),
    val recommendedNewsUiState: List<NewsItemUiState>?= emptyList(),
    val isLoading:Boolean=false,
    val error:String?=null
)
data class NewsUiState(
    val title:String="",
    val imageUrl:String="",
    val content:String="",
    val category: String="",
    val url: String=""
)

data class NewsItemUiState(
    val title:String="",
    val imageUrl:String="",
    val content:String="",
    val category: String="",
    val url: String="",
    val publishedAt:String="",
)
