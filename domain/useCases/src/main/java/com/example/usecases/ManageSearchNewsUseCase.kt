package com.example.usecases

import com.example.entities.news.NewsItemEntity
import javax.inject.Inject

class ManageSearchNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun getSearchNews(keyword: String): List<NewsItemEntity> {
        return newsHiveRepository.searchNews(keyword, "en", "published_desc")
            .distinctBy { it.news.title }
    }
}