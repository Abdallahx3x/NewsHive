package com.example.usecases

import javax.inject.Inject

class GetCategoryNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun getLastCategoryNews(categoryName: String) = newsHiveRepository
        .getCategoryNews(categoryName, "published_desc", "", "en")
        ?.distinctBy { it.news.title }
}