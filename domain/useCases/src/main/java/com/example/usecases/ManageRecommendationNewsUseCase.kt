package com.example.usecases

import javax.inject.Inject

class ManageRecommendationNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun getRecommendationNews() = newsHiveRepository
        .getLatestNews(
            sort = SORT,
            language = LANGUAGE
        ).drop(5).take(4).distinctBy { it.news.title }

    suspend fun getAllRecommendationNews() = newsHiveRepository.getAllLatestNews(
        sort = SORT,
        language =LANGUAGE
    )

    companion object {
        const val SORT = "published_desc"
        const val LANGUAGE = "en"
    }
}