package com.example.viewmodel.home

interface HomeInteraction {
    fun onClickViewAll()
    fun onClickBreakingNewsItem(title:String,content:String,imageUrl:String,url:String,breakingNewsUiState: NewsUiState)
    fun onClickRecommendedNewsItem()
}