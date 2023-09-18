package com.example.viewmodel.discover

data class DiscoverUiState(
    val pageIndex:Int?=0,
    val sportsNews: List<CategoryNewsUiState>?= emptyList(),
    val scienceNews: List<CategoryNewsUiState>?= emptyList(),
    val healthNews: List<CategoryNewsUiState>?= emptyList(),
    val technologyNews: List<CategoryNewsUiState>?= emptyList(),
    val businessNews: List<CategoryNewsUiState>?= emptyList(),
    val entertainmentNews: List<CategoryNewsUiState>?= emptyList(),
    val isLoading:Boolean=false,
    val error:String?=null
)



data class CategoryNewsUiState(
    val title:String?="",
    val imageUrl:String?="",
    val publishedAt:String?="",
    val categoryName: String?=""
)
