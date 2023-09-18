package com.example.viewmodel

import com.example.entities.news.LatestNews
import com.example.viewmodel.discover.CategoryNewsUiState
import com.example.viewmodel.home.BreakingNewsUiState
import com.example.viewmodel.home.RecommendedNewsUiState

@JvmName("breakingNewsToBreakingNewsUiState")
fun LatestNews.toUiState(): BreakingNewsUiState {
   return BreakingNewsUiState(
        title=this.title,
        imageUrl=this.imageUrl,
        category=this.title
    )
}
@JvmName("breakingNewsToBreakingNewsUiState")
fun List<LatestNews>.toUiState():List<BreakingNewsUiState> = this.map { it.toUiState() }



@JvmName("classifiedNewsToRecommendedNewsUiState")
fun LatestNews.toRecommendationUiState(): RecommendedNewsUiState {
    return RecommendedNewsUiState(
        title=this.title,
        imageUrl=this.imageUrl,
        category=this.category,
        publishedAt = this.publishedAt,
    )
}
@JvmName("classifiedNewsToRecommendedNewsUiState")
fun List<LatestNews>.toRecommendationUiState():List<RecommendedNewsUiState> = this.map { it.toRecommendationUiState() }







@JvmName("classifiedNewsToDiscoverNewsUiState")
fun LatestNews.toDiscoverNewsUiState(): CategoryNewsUiState {
    return CategoryNewsUiState(
        title=this.title,
        imageUrl=this.imageUrl,
        categoryName=this.category,
        publishedAt = this.publishedAt,
    )
}
@JvmName("classifiedNewsToDiscoverNewsUiState")
fun List<LatestNews>.toDiscoverNewsUiState():List<CategoryNewsUiState> = this.map { it.toDiscoverNewsUiState() }